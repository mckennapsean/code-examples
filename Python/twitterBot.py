import tweepy
import markovify
import sys, random, time

ck = ''
cs = ''
ak = ''
ase = ''
auth = tweepy.OAuthHandler(ck, cs)
auth.set_access_token(ak, ase)
api = tweepy.API(auth)
# print(api.statuses_lookup(100))
# print(api.me())

with open('https://raw.githubusercontent.com/gastonstat/StarWars/master/Text_files/EpisodeIV_dialogues.txt') as f:
  ep4 = f.read()
with open('https://raw.githubusercontent.com/gastonstat/StarWars/master/Text_files/EpisodeV_dialogues.txt') as f:
  ep5 = f.read()
with open('https://raw.githubusercontent.com/gastonstat/StarWars/master/Text_files/EpisodeVI_dialogues.txt') as f:
  ep6 = f.read()

ep4lines = ep4.split('\n')
ep5lines = ep5.split('\n')
ep6lines = ep6.split('\n')

mep4 = markovify.Text(ep4)
mep5 = markovify.Text(ep5)
mep6 = markovify.Text(ep6)
model1 = markovify.combine([mep4, mep5], [1, 1])
model2 = markovify.combine([model1, mep6], [2, 1])

for i in range(3):
  print(model2.make_short_sentence(280))

# api.update_status(model2.make_short_sentence(280))