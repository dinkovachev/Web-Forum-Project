use forum;

INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (1, 'John', 'Doe', 'john.doe@example.com', 'john_doe', 'pass1', 0, 0, 1);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (2, 'Jane', 'Smith', 'jane.smith@example.com', 'jane_smith', 'pass2', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'alice_johnson', 'pass3', 1, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (4, 'Geori', 'Tomov', 'georgi.tomov@example.com', 'georgi_tomov', 'pass4', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (5, 'Berta', 'Morar', 'berta.morar@example.com', 'berta_morar', 'pass5', 1, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (6, 'Serena', 'Hayes', 'serena.hayes@example.com', 'serena_hayes', 'pass6', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (7, 'Zella', 'Kertzman', 'zella.kertzman@example.com', 'zella_kertzman', 'pass7', 1, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (8, 'Petq', 'Todorova', 'petq.todorova@example.com', 'petq_todorova', 'pass8', 0, 0, 1);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (9, 'Mark', 'Zuckerberg', 'marko123@example.com', 'mark_zuckerberg', 'pass9', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (10, 'Petar', 'Stoyanov', 'petar.stoyanov@example.com', 'petar_stoyanov', 'pass10', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (11, 'Lionel', 'Messi', 'lionel.messi@example.com', 'lionel_messi', 'pass11', 0, 0, 1);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (12, 'Georgi', 'Asparuhov', 'georgi.asparuhov@example', 'georgi_asparuhov', 'pass12', 0, 0, 0);


INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (1, 'Will Leeds be relegated?', 'Leeds are beginning the season very strongly.
It reminds me Olympique de Marseille with Bielsa. They were on top of the french league for months and they finished exhausted and finally finished in 3rd place.
I can\'t imagine Leeds being that strong all season. I think they will get exhausted and will have a very difficult second part of the season.
They may be relegated...

What do you think?', '2024-01-24 11:45:52', 0, 8);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (2, 'League 2 Away Support Thread 2023/24', 'For all discussion, averages and general chat on league 2 away support for the 14/15 season, please use this thread.
', '2024-01-23 10:46:52', 0, 11);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (3, 'Oakey’s Predicta League Two 2023/24', 'How do you think your teams\' performance will go over a season ?
THE RULES.....
1-You\'re either RIGHT or WRONG...:woot:
The ultimate winner will have the highest correct predicta percentage over the season.....:2thumb:
2-Predict your OWN team\'s result.....WIN,LOSS or DRAW.....:thumbs:
3-You get 3 points ADDED to your total if you are right...:hypo::hypo::hypo:
4-You get 3 points DEDUCTED if you are wrong....:devil::devil::devil:
Miss or Fail to predict you will be DEDUCTED 6 points ... :devil::devil::devil::devil::devil::devil:
5-You Can Use The Joker To Double Up Your Correct Predicta ....:bdick:
But You Will Be Deducted Double Points If Incorrect!!...:eyes:
Your points total will help in deciding your place in the league.....:dry:
6-Predictions must be received before kick-off,or else void. ..:fing:
7-ONE predicta at a time...:crazy:
8-Posts must have BOTH team names involved in the game, plus the date,then your predicta .Please predict ...WIN,LOSS or DRAW... nothing else please!!....:ffs:
9-This is open to anybody,across the forums, who fancy\'s a go .:fish:
10-It\'s About Points... But ...Win Percentages Takes Precedence!!...:box:
PS...in the event of a close end-of the-season finish...predictas will be required to be submitted by private messages only...:animatedf:
PPS...if you MISS 3 games you will be DELETED from the predicta game...:bye:', '2024-01-22 15:47:52', 0, 10);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (4, 'Relegation Thread 23/24 Sponsored by Sadford City', 'Bring on the Halifax! Can\'t wait to tell all the minnows how many season tickets we sell, lads.', '2024-01-21 16:48:52', 0, 6);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (5, 'Third Week for National Football', 'Owned by the Rooney family, the Steelers team could face a wavering composure for the upcoming league. All three players including Antonio Brown, Le\'Veon Bell and Ben Roethlisberger upheld their team by showcasing immaculate and unswerving performance since the past seasons and the Pittsburgh guys also encompass a handful of other alternatives which harmonize with the trio.

Collegiate player Martavis Bryant is the big game’s contender who has stupendous path chasing tactic and stamina to make it big at the forthcoming games. Bryant averages with 21.0 yards for each receiving following the initial couple of games for 2016-17. 23-year old Jesse James comes close with 10 receptions which includes two touchdowns making a superb tight end player.', '2024-01-20 17:49:52', 0, 11);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (6, 'American Football NFL', 'Just wondering if anyone else watches American football?

Never really seen it before but since its been on t.v over here (and getting bigger) I\'ve actually enjoyed watching it. :)', '2024-01-27 19:41:52', 0, 12);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (7, 'Cowboys vs Washington Live Stream: How to watch', 'Cowboys vs Washington Live Stream: How to watch from anywhere Free on Reddit, Thanks Giving Games, NFL top matches week 12
In this Thanks giving day in week 12 one of the oldest rivalries in the NFL to look forward to on Thursday, as the Washington RedskinsFootball Team travels to Texas to take on the Dallas Cowboys. If you are looking for live streaming Thursday thanksgiving football match live stream then you have landed here in the right place.', '2024-01-27 14:03:40', 1, 2);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (8, 'The NHL Thread', 'Not sure if anybody who posted in this has made it over to the new forum.', '2024-01-05 16:05:01', 0, 3);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (9, 'NHL 2015/16', 'Only a month or so away from puck drop, and an interesting summer - the next hockey Jesus is in Edmonton, along with a bunch of other people that don\'t suck. What is that? The standings won\'t look right without the Oilers at the bottom.

Expansion applications are in for Quebec (yay!) and Las Vegas (WTF?), and I\'d expect them both to sign on for the NHL\'s centenary year in 2017.

Meanwhile, we Penguin types have picked up Phil "The Thrill" Kessel in the hopes we can score more than 4 goals in a playoff season. We shall see....', '2024-01-11 10:05:12', 0, 7);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (10, 'The mid-table thread', 'Where all the real prestige is to be found.

Sod that promotion and relegation nonsense, it\'s averageness that is truly to be celebrated, and no-one can be more average than the winner of the mid-table trophy.

A reminder of the rules:

First team to hit the beach, whereby you\'re too crap to stand any chance mathematically of going up, but you\'re not crap enough to be able to go down.
In the event of a tie (which is pretty common, especially in a league stuffed with such utterly average teams), the winner is crowned by having the GD that is closest to zero, to reflect TRUE mediocrity.
If that\'s still a tie, then the team closest to the sacred mid-table havens of 12th or 13th.
...and if that STILL hasn\'t decided it, then the team with the most draws.
There are some who believe in the "first past the post" method of determining this, but I think that\'s far too open to abuse and swings far too arbitrarily on one game - what happens if it\'s a late kick-off or something? So bollocks to that - fairness must be applied.', '2023-12-29 08:05:37', 1, 4);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (11, 'Tuesday 13th February - Fixtures', 'Full set of fixtures this midweek.
Derby (H) - Never know what to expect with City nowadays, would take a point, but know all 3 could well be ours if the defence shows up.
Don\'t need strikers as we just get own goals and massive deflections for us.', '2024-01-25 12:06:05', 0, 1);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (12, 'Worst team in the league', 'Who is the worst team in L1 so far this season in your opinion?', '2023-02-07 11:06:34', 0, 5);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (13, 'football hooliganism survey', 'HI,
I am a student currently studying football business management and have been tasked to make a survey in a chosen topic.
i would greatly appreciate it if you took the time out of your day to take part in this.
many thanks', '2024-02-18 18:17:51', 0, 1);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (14, 'The Cricket Thread', 'So then England have beaten India over this summer and now head to play a 5 match ODI series. Not sure I like the balance of the side with Cook/Ballance/Tredwell playing, there are better ODI players in the county domestic scene at the moment. Taylor, Vince, Roy, Hales (who finally has his chance).', '2024-02-18 22:25:50', 0, 1);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (15, 'Sports Personality of the Year', 'Another cyclist winning - they all seem to do quite well apart from Chris Froome who is the most successful British (maybe the people who vote don\'t see him as that) rider of them all.

Hamilton 2nd (he can win next year after another title) and Kane 3rd (suspect they didn\'t want to see a footballer win anyway - never seem to do that well)

Netball got more awards than the England football team which some might find strange but it was all a public vote.', '2024-02-18 22:43:42', 0, 1);



INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (1, 'Think they will finish midtable personally. Bielsa\'s teams have tired as the season goes on and others will wisen up to how they play.', '2024-01-24 11:45:52', 1, 11, 1);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (2, 'No chance, they are way too good.', '2024-01-24 11:45:52', 0, 3, 1);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (3, 'Bury took 321 fans to Wycombe today, a 400 mile round trip, never stopped singing, amazing away support as ever!', '2024-01-24 11:45:52', 0, 12, 9);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (4, 'Wycombe not a great team for us as my preview stated, happy with a point, sounded like a good game though, we should have more than enough to go up this year, although only 6 subs today, inc GK and a youth player, I can see a few loans coming in.', '2024-01-24 11:45:52', 0, 1, 10);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (5, 'Welcome back my friend
Hope you’re better
Can you help to get this stickied?
Ta ever so ..', '2024-01-24 11:45:52', 0, 11, 11);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (6, 'Yep i played las season and won it easily mate.', '2024-01-24 11:45:52', 0, 10, 12);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (7, 'Got a suspicion we might feature in this thread too.', '2024-01-24 11:45:52', 0, 4, 7);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (8, 'Yeah, another big 6 pointer that.

Although today was meant to be a big ‘un too, but no one seemed to have told our players that.', '2024-01-24 11:45:52', 0, 6, 8);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (9, 'Patriotic Patriots Rule the Roost
', '2024-01-24 11:45:52', 0, 5, 9);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (10, 'The New Orleans Saints have unarguably the most putrid guard strategies in the league, while the Houston boys encompass the finest. Speaking of a solitary player’s performance, the trio of Whitney Mercilus, Jadeveon Clowney and Watt have taken the Texans to the top-slots of the roster. After the injury of New England’s player Rob Gronkowski during the second week, Boston Globe’s Jim McBride considered it as “nothing serious” since injuries can be complicated. Furthermore, Rob’s account of injuries could have given head trainer Bill Belichick all the reasons for apprehension to assign him any further tasks.', '2024-01-24 11:45:52', 0, 7, 10);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (11, 'I watch it yeah (and occasionally play it actually!), it\'s really good fun to watch and the NFL is such a perfect rounded franchise!', '2024-01-24 11:45:52', 1, 9, 1);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (12, 'Being on sky and channel 4 now shows how big it\'s getting, would love to go to the games at Wembley.', '2024-01-24 11:45:52', 1, 10, 1);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (13, 'Really surprised by the Netball awards. Maybe it didn\'t receive as much publicity but first I\'d heard of it was last night?', '2024-02-19 13:25:14', 0, 1, 15);



INSERT INTO forum.users_posts(user_id, post_id) VALUES(1,11);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(2,7);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(3,8);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(4,10);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(5,12);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(6,4);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(7,9);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(8,1);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(10,3);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(11,2);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(11,5);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(12,6);

INSERT INTO forum.users_comments(user_id, comment_id) VALUES(1,4);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(3,2);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(4,7);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(5,9);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(6,8);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(7,10);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(9,11);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(10,6);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(10,12);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(11,1);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(11,5);
INSERT INTO forum.users_comments(user_id, comment_id) VALUES(12,3);


INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (1, 1, 1);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (2, 1, 2);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (3, 1, 3);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (4, 1, 4);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (5, 2, 1);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (6, 2, 2);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (7, 2, 3);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (8, 2, 4);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (9, 2, 5);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (10, 2, 6);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (11, 2, 7);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (12, 3, 8);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (13, 3, 9);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (14, 3, 10);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (15, 3, 12);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (16, 3, 1);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (17, 3, 2);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (18, 3, 3);
INSERT INTO forum.likes (like_id, liked_post_id, author_id) VALUES (19, 3, 5);
