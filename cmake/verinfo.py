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

def generate_verinfo(project, version, revision):
    artifacts = [ jarname (version, jar) for jar in jars ]
    d = { 'project': 'OG-Maths', 'revision': revision, 'version': version, \
          'platforms': [platform_code()], 'artifacts': artifacts }
    return d

def main():
    if len(sys.argv) != 3:
        print "Usage: verinfo.py <output_file> <version>"
        return 1

    process = subprocess.Popen(['git', 'rev-parse', 'HEAD'], stdout=subprocess.PIPE)
    rev, unused_err = process.communicate()
    retcode = process.poll()
    if retcode:
        raise RuntimeError('Error getting SHA1 of git HEAD')

    with open(sys.argv[1],'w') as f:
        f.write(dump(generate_verinfo('OG-Maths', sys.argv[2], rev.strip()), Dumper=Dumper))
    return 0

if __name__ == '__main__':
    sys.exit(main())
