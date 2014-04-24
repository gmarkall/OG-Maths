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

DEBUG = False

def main():
    if len(sys.argv) < 4:
        print "Usage: combine.py <combined blob output> <build number> [source blobs]"
        exit(1)
    
    newblobname = sys.argv[1]
    buildnum = sys.argv[2]
    sourceblobs = sys.argv[3:]

    verinfos = read_version_info(sourceblobs)
    validate_version_info(verinfos)
    newverinfo = construct_combined_verinfo(verinfos)

    return 0

def read_version_info(sourceblobs):
    """Read the version info from each of the specified source blobs."""
    verinfos = {}
    for blob in sourceblobs:
        with zipfile.ZipFile(blob, 'r') as zipf:
            with zipf.open('verinfo.yaml') as verinfo:
                verinfos[blob] = load(verinfo, Loader=Loader)
                if DEBUG:
                    print "\nRead verinfo.yaml from %s:\n" % blob
                    pprint(verinfos[blob])
    return verinfos

def validate_version_info(verinfos):
    """Validate version info - the project, revision, and version should match across
    blobs and across each subproject in the blobs."""

    def important(attr):
        """Returns true if it is important for an attribute to be equal across blobs."""
        return attr in ['project', 'revision', 'version']
    
    # Use the first blob's verinfo as a reference
    refblob = verinfos.keys()[0]
    ref = verinfos[refblob]
    for blob, verinfo in verinfos.iteritems():
        for k, v in ref.iteritems():
            if important(k) and v != verinfo[k]:
                raise ValueError('Reference blob %s key %s = %s does not match blob %s which has value %s' %
                                 (refblob, k, v, blob, verinfo[k]))
            if k == 'subprojects':
                # Check subprojects.
                # Check the subprojects are the same subprojects in each project
                refsps = set([ s['project'] for s in ref['subprojects'] ])
                versps = set([ s['project'] for s in verinfo['subprojects'] ])
                if refsps != versps:
                    raise ValueError('Reference blob %s has subprojects %s but blob %s has subprojects %s' %
                                     (refblob, refsps, blob, versps))
                # Check subproject keys match
                for refsubproject in ref['subprojects']:
                    # Find subproject from verinfo
                    for sp in verinfo['subprojects']:
                        if sp['project'] == refsubproject['project']:
                            subproject = sp
                    for ks, vs in refsubproject.iteritems():
                        if important(ks) and vs != subproject[ks]:
                            raise ValueError('Reference blob %s key %s.%s = %s does not match blob %s which has value %s' %
                                             (refblob, k, ks, v, blob, subproject[ks]))
    
def construct_combined_verinfo(verinfos):
    """Constructs verinfo for the combined blob based on all constituent verinfos.
    It is expected that the verinfos are already validated."""

    # Use the first verinfo as reference
    ref = verinfos[verinfos.keys()[0]]

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
    
    # Add the artifact names.
    basename = '%s-%s' % (newverinfo['project'].lower(), newverinfo['version'])
    artifacts = [ s % basename for s in ('%s.jar', '%s-javadoc.jar', '%s-sources.jar', '%s-tests.jar') ]
    newverinfo['artifacts'] = artifacts

    if DEBUG:
        print "\nNew verinfo:\n"
        pprint(newverinfo)

    return newverinfo
    
if __name__ == '__main__':
    sys.exit(main())
