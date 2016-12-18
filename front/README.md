# Organisation

Le projet est organisé selon les fonctionalités, ce qui veut dire chaque fonction (metier) a sa propore dossier. Et comme ça on garde la clareté de l'application.

## Getting Started

To get you started you can simply clone the ecom-front repository and install the dependencies:

-- Install git and node in workstation.

### Clone ecom-front

```
git clone https://bitbucket.org/koudair/ecom-front.git
cd ECOM-Front
```

The `depth=1` tells git to only pull down one commit worth of historical data.

### Install Dependencies

We have two kinds of dependencies in this project: tools and angular framework code.  The tools help
us manage and test the application.

* We get the tools we depend upon via `npm`, the [node package manager][npm].
* We get the angular code via `bower`, a [client-side code package manager][bower].

We have preconfigured `npm` to automatically run `bower` so we can simply do:

```
npm install
```

Behind the scenes this will also call `bower install`.  You should find that you have two new
folders in your project.

* `node_modules` - contains the npm packages for the tools we need
* `app/bower_components` - contains the angular framework files

*Note that the `bower_components` folder would normally be installed in the root folder but
ecom-front changes this location through the `.bowerrc` file.  Putting it in the app folder makes
it easier to serve the files by a webserver.*

### Run the Application

```
npm start
```

Now browse to the app at `http://localhost:8000/index.html`.

## Testing

There are two kinds of tests in the ecom-front application: Unit tests and end-to-end tests.

### Running Unit Tests

```
npm test
```

```
npm run test-single-run
```


### End to end testing

```
npm start
```

```
npm run update-webdriver
```

```
npm run protractor
```

## Updating Angular

```
npm update
```

This will find the latest versions that match the version ranges specified in the `package.json` file.

You can update the Angular dependencies by running:

```
bower update
```

This will find the latest versions that match the version ranges specified in the `bower.json` file.


## Loading Angular Asynchronously

The ecom-front project supports loading the framework and application scripts asynchronously.  The
special `index-async.html` is designed to support this style of loading.  For it to work you must
inject a piece of Angular JavaScript into the HTML page.  The project has a predefined script to help
do this.

```
npm run update-index-async
```

This will copy the contents of the `angular-loader.js` library file into the `index-async.html` page.
You can run this every time you update the version of Angular that you are using.


### Running the App during Development

```
sudo npm install -g http-server
```

```
http-server -a localhost -p 8000
```

Alternatively, we can choose to configure our own webserver, such as apache or nginx. Just
configure our server to serve the files under the `app/` directory.