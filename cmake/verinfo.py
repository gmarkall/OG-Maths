#
# Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#

import platform, sys, subprocess
from yaml import load, dump
try:
    from yaml import CLoader as Loader, CDumper as Dumper
except ImportError:
    from yaml import Loader, Dumper

jars = [ None, 'javadoc', 'sources', 'tests' ]

def jarname(version, suffix):
    if suffix is None:
        suffix = ''
    else:
        suffix = '-%s' % suffix
    return 'jars/og-maths-%s-%s%s.jar' % (platform_code(), version, suffix)

def platform_code():
    p = platform.system()
    if p == 'Linux':
        return 'lnx'
    elif p == 'Darwin':
        return 'osx'
    else:
        return 'win'

def get_subprojects(lapack_verinfo_file):
    with open(lapack_verinfo_file, 'r') as f:
        lapack_verinfo = load(f, Loader=Loader)
    if len(lapack_verinfo['platforms']) != 1 or lapack_verinfo['platforms'][0] != platform_code():
        raise RuntimeError('OG-Lapack platform mismatch')
    return [{ 'project': lapack_verinfo['project'], 'revision': lapack_verinfo['revision'] }]

def generate_verinfo(project, version, revision, lapack_verinfo_file):
    artifacts = [ jarname (version, jar) for jar in jars ]
    subprojects = get_subprojects(lapack_verinfo_file)
    d = { 'project': 'OG-Maths', 'revision': revision, 'version': version, \
          'platforms': [platform_code()], 'artifacts': artifacts,
          'subprojects': subprojects }
    return d

def main():
    if len(sys.argv) != 4:
        print "Usage: verinfo.py <output_file> <version> <lapack_verinfo>"
        return 1

    process = subprocess.Popen(['git', 'rev-parse', 'HEAD'], stdout=subprocess.PIPE)
    rev, unused_err = process.communicate()
    retcode = process.poll()
    if retcode:
        raise RuntimeError('Error getting SHA1 of git HEAD')

    with open(sys.argv[1],'w') as f:
        f.write(dump(generate_verinfo('OG-Maths', sys.argv[2], rev.strip(), sys.argv[3]), Dumper=Dumper))
    return 0

if __name__ == '__main__':
    sys.exit(main())
