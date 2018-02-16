# Import the necessary package to process data in JSON format
try:
    import json
except ImportError:
    import simplejson as json

# Import the necessary methods from "twitter" library
from twitter import Twitter, OAuth, TwitterHTTPError, TwitterStream

# Variables that contains the user credentials to access Twitter API 
config = {}
execfile("config.py", config)

oauth = OAuth(config["access_key"], config["access_secret"], config["consumer_key"], config["consumer_secret"])

# Initiate the connection to Twitter Streaming API
twitter_stream = TwitterStream(auth=oauth)
twitter_userstream = TwitterStream(auth=oauth, domain='userstream.twitter.com')

# Get a sample of the public data following through Twitter
#iterator = twitter_stream.statuses.sample()
#iterator = twitter_userstream.statuses.sample()
#iterator = twitter_stream.statuses.filter(track="Trump", language="en")
# Print each tweet in the stream to the screen 
# Here we set it to stop after getting 1000 tweets. 
# You don't have to set it to stop, but can continue running 
# the Twitter API to collect data for days or even longer. 
tweet_count = 20
for tweet in iterator:
    tweet_count -= 1
    # Twitter Python Tool wraps the data returned by Twitter 
    # as a TwitterDictResponse object.
    # We convert it back to the JSON format to print/score
    #print json.dumps(tweet)  
    
    # The command below will do pretty printing for JSON data, try it out
    # print json.dumps(tweet, indent=4)
    print json.dumps(tweet, indent=4)
       
    if tweet_count <= 0:
        break 
