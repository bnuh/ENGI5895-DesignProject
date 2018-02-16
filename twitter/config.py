from twitter import Twitter, OAuth, TwitterHTTPError, TwitterStream

try:
    import json
except ImportError:
    import simplejson as json

# Variables that contains the user credentials to access Twitter API
consumer_key = '20yNRIOurzQaKzs9t7C4HXWuV'
consumer_secret = 'Pl9z3CqDrj5QSBYkMDaKxT6cx4AUSlf4Jm7UOf8ovkdgcuQvcD'
access_key = '1942242924-aQgcIo4phlvOu38IGOphtIazT3mSiWUAnkTypHX'
access_secret = 'zTY5tAJKVG6aGW44DIEHzprxBDXZzpxVQfA44dcjl6KSt'

oauth = OAuth(access_key, access_secret, consumer_key, consumer_secret)
