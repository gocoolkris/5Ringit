import praw
import time

def filter(str):
    return str.replace("'","''");
	
def returnQuery(pid, usrid, title, descrp, link):
    score = 0
    title = filter(title)
    descrp = filter(descrp)
    link = filter(link)
    query = "INSERT INTO POST VALUES({0},{1},'{2}','{3}','{4}',current_timestamp,{5});\n"
    return query.format(pid,usrid,title,descrp,link,score)


r = praw.Reddit(user_agent='Hi')

pid=25000
already_done = []
topics=['funny','pics','politics','gaming','technology','gifs','science','music','movies','reactiongifs','videos','WTF','aww','AdviceAnimals','worldnews']
#topics=['funny','pics','politics','gaming','technology','gifs','science','music','movies','reactiongifs']
#topics=['funny','pics','gaming','technology','gifs','music','movies','reactiongifs']
for i in range(15):
    topic=topics[i];
    #print "\n\n"+"The Tpoic is "+topic+"\n\n"
    subreddit = r.get_subreddit(topic)
    for submission in subreddit.get_hot(limit=1000):
        if submission.id not in already_done:
            try:
                result = returnQuery(pid,i+500000,submission.title,'',submission.url)
		#print(submission.title);
                already_done.append(result)
                pid=pid-1;
            except:
                continue

			
    
    time.sleep(60)

for x in already_done:
	try:	
		print x
	except:
		print ""



