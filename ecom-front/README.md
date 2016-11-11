# Getting Started

Install Git, Node.

### Clone

```
git clone https://bitbucket.org/koudair/ecom-front.git
cd ECOM-Front
```

### Install Dependencies

We have two kinds of dependencies in this project: tools and angular framework code.  The tools help

* We get the tools we depend upon via `npm`, the [node package manager][npm].
* We get the angular code via `bower`, a [client-side code package manager][bower].

```
npm install
```
___
# Bug
I get problem with this command, i have run 'sudo apt install nodejs-legacy' to fix it.
Problem is due because node not exist, but nodejs have been already installed.
___

Behind the scenes this will also call `bower install`.  You should find that you have two new
folders in your project.

* `node_modules` - contains the npm packages for the tools we need
* `app/bower_components` - contains the angular framework files

*Note that the `bower_components` folder would normally be installed in the root folder but
angular-seed changes this location through the `.bowerrc` file.  Putting it in the app folder makes
it easier to serve the files by a webserver.*

### Run the Application

We have preconfigured the project with a simple development web server.  The simplest way to start
this server is:

```
npm start
```

Now browse to the app at `http://localhost:8000/index.html`.