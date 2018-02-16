try:
    import json
except ImportError:
    import simplejson as json

# Import the necessary methods from "twitter" library
from twitter import Twitter, OAuth, TwitterHTTPError, TwitterStream

# Variables that contains the user credentials to access Twitter API
ACCESS_TOKEN = '1942242924-K1pi57z1SljvWDib3Y9XgIEYcgK6uTQy7jiLHMd'
ACCESS_SECRET = 'aIZCXgTnQqKWMTaYKcMSkm7Cf5yqZw5w0GqPhvzm7mj9L'
CONSUMER_KEY = '20yNRIOurzQaKzs9t7C4HXWuV'
CONSUMER_SECRET = 'Pl9z3CqDrj5QSBYkMDaKxT6cx4AUSlf4Jm7UOf8ovkdgcuQvcD'

oauth = OAuth(ACCESS_TOKEN, ACCESS_SECRET, CONSUMER_KEY, CONSUMER_SECRET)

# Initiate the connection to Twitter REST API
twitter = Twitter(auth=oauth)

# Search for latest tweets about "#nlproc"
results = twitter.search.tweets(q='#nlproc', result_type='recent', lang='en', count=10)
#print(results)
world_trends = twitter.trends.available(_woeid=1)
#print(world_trends)
sfo_trends = twitter.trends.place(_id = 2487956)
print json.dumps(sfo_trends, indent=4)
