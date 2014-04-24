#
# Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#

import sys, os, zipfile
from pprint import pprint
from yaml import load, dump
try:
    from yaml import CLoader as Loader, CDumper as Dumper
except ImportError:
    from yaml import Loader, Dumper

DEBUG = True

def main():
    if len(sys.argv) < 4:
        print "Usage: combine.py <combined blob output> <build number> [source blobs]"
        exit(1)
    
    newblobname = sys.argv[1]
    buildnum = sys.argv[2]
    sourceblobs = sys.argv[3:]

    # Read version info from each source blob:
    verinfos = {}
    for blob in sourceblobs:
        with zipfile.ZipFile(blob, 'r') as zipf:
            with zipf.open('verinfo.yaml') as verinfo:
                verinfos[blob] = load(verinfo, Loader=Loader)
                if DEBUG:
                    print "\nRead verinfo.yaml from %s:\n" % blob
                    pprint(verinfos[blob])
                    

    # Validate version info - everything should match apart from the platform and build number
    def important(attr):
        return attr in ['project', 'revision', 'version']
    
    # Use the first one we find as a reference (arbitrarily)
    ref = verinfos[sourceblobs[0]]
    for blob, verinfo in verinfos.iteritems():
        for k, v in ref.iteritems():
            if important(k) and v != verinfo[k]:
                raise RuntimeError('Reference blob %s key %s = %s does not match blob %s which has value %s' %
                                   (sourceblobs[0], k, v, blob, verinfo[k]))
            if k == 'subprojects':
                # Check subprojects
                for refsubproject in ref['subprojects']:
                    # Find subproject from verinfo
                    for sp in verinfo['subprojects']:
                        if sp['project'] == refsubproject['project']:
                            subproject = sp
                    for ks, vs in refsubproject.iteritems():
                        if important(ks) and vs != subproject[ks]:
                            raise RuntimeError('Reference blob %s key %s.%s = %s does not match blob %s which has value %s' %
                                               (sourceblobs[0], k, ks, v, blob, verinfo['subprojects'][k]))
    
    # Construct new verinfo

    # Top-level artifact info
    newverinfo = {}
    newverinfo['project'] = ref['project']
    newverinfo['revision'] = ref['revision']
    newverinfo['version'] = ref['version']
    
    # Add subproject revisions
    newverinfo['subprojects'] = {}
    for s in ref['subprojects']:
        newverinfo['subprojects'][s['project']] = s['revision']

    # Add platform build numbers
    # First some space to put the build numbers of sub projects
    subprojects = {}
    for s in ref['subprojects']:
        subprojects[s['project']] = {}
    # Now the empty dict for build numbers
    newverinfo['buildnumbers'] = { 'subprojects': subprojects }
    # And populate these with actual numbers
    for blob, verinfo in verinfos.iteritems():
        platform = verinfo['platforms'][0]
        newverinfo['buildnumbers'][platform] = verinfo['buildnumber']
        for subproject in verinfo['subprojects']:
            newverinfo['buildnumbers']['subprojects'][subproject['project']][platform] = subproject['buildnumber']
    
    if DEBUG:
        print "\nNew verinfo:\n"
        pprint(newverinfo)

    return 0
    
if __name__ == '__main__':
    sys.exit(main())
