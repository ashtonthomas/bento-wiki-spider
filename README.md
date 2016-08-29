# How many hops to Philosophy

https://bento-wiki-spider.herokuapp.com

*This repo sucks because I was unable to figure out how to keep the compiled folders out of git and still be able to deploy to Heroku (`public/` and `target/`)*


## This Repo

- The angular code can be found in [/source](https://github.com/ashtonthomas/bento-wiki-spider/tree/master/source)
- The angular project uses [brousalis/bendystraw](https://github.com/brousalis/bendystraw) a collection of gulp tasks, configurations
- The styling leverages [bellycard/rolodex](https://github.com/bellycard/rolodex) which is a sass library using functional css
- `gulp build` will compile front-end resources to `/public`
- The backend spark files can be found in [src/main/java/sparq](https://github.com/ashtonthomas/bento-wiki-spider/tree/master/src/main/java/sparq)

### Done
- Node, NPM, Bower, Gulp
- [brousalis/bendystraw](https://github.com/brousalis/bendystraw) integration (deployment optimizations and more)
- [bellycard/rolodex](https://github.com/bellycard/rolodex) integration
- Base Angular setup with ui-router
- Angular communication with Spark backend
- Spark Setup
- Heroku deployment
- Use session for temp storage (sorry)
- Trace wiki route
- Display hops in angular app


### Not Done
- Hibernate connection with Derby (failed here: https://github.com/ashtonthomas/bento-wiki-spider/pull/1)
- Need a better way to develop locally and be able to edit angular code and java code without rebuilding
- Find a way to build on deploy and not commit compiled resources on every change (this is so annoying)
- Show loading while parsing / or show progress (but that will take more changes)



## This is the start of the java hack project.

- Full-stack java-based app
- Web view that accepts a wikipedia url (starting point)
- recursively retrieve the first lowercase link in the main text until the [Philosophy](https://en.wikipedia.org/wiki/Philosophy) page is reached
- persist and present the path taken (include datastore)
- handle edge cases or special scenarios
- display sufficient design and organization of application
- will be mostly new technologies and I get to use an IDE again (not even sure what is on my personal computer...)

## Technologies to tinker with:
- Spark
- Hibernate (nvm)
- Angular (1.5)
- MySQL (maybe use an in memory data store or filestore) - maybe Postgres if I'm deploying to Heroku
- Deploy to Heroku?


## References:
- https://github.com/rayokota/generator-angular-spark
- https://sparktutorials.github.io/2016/06/26/ajax-without-writing-javascript.html
- https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/
- https://sparktutorials.github.io/2015/08/24/spark-heroku.html
- https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java
- http://ramonaharrison.github.io/accesscode/java/http/wikipedia/2015/03/27/wikipedia-philosophy/
- https://github.com/ramonaharrison/wikipedia-philosophy

## Notes:
- Oh lawd my personal computer is so out of date (old ass brew, node, first version of Atom - this should be fun - this is time travel)
- :heart: com.foo.bar.i.missed.java.class


## Running Angular in Dev
- I wasn't able to figure out how to run the project from the command line or with maven
- I just imported the project into eclipse and ran the App file..

```
brew // all the things (node, npm)
npm install -g bower
npm install --global gulp-cli

npm install
bower install
gulp
```

open up:

`http://localhost:4567`


## More Getting Started

*Make sure npm, bower, gulp is running above

```
gulp build
mvn clean package

//run the App class
```

## Deploying

```
gulp build
mvn clean package

// I couldn't figure out how to use heroku deploy and have it build target (so will just use git and stuff target into git repo)
git push heroku master

// and when things catch fire
heroku logs --tail -a bento-wiki-spider
```

Or if you really need to get your hands dirty

```
heroku run bash -a bento-wiki-spider

```

## When a dev server hangs on the port

```
kill -kill `lsof -t -i tcp:4567`
```
