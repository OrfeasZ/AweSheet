# Awesome Sheet
![AweSheet](http://i.nofate.me/uRjKMvf0.png)

Awesome Sheet (stylized **AweSheet**) is an open-source spreadsheet editing and management application which aims to assist individuals and organizations when performing logistic tasks, by not only providing them with a variety of advanced tools and functions, but by also greatly enhancing user experience and productivity through a robust and simple-to-use user interface.

With AweSheet, users can easily access, create, and edit their spreadsheets. A spreadsheet is a table of values arranged in rows and columns. In AweSheet, these values are not restricted to only static user-defined data, but can also have a direct relationship with other values within the spreadsheet. This allows users to simplify common spreadsheet tasks and create huge datasets consisting of dynamically computed content, leveraging the power of advanced functions, formulas, and expressions.

This project was initially developed as part of an assignment for the Software Engineering course at University of Ioannina.

## Supported Platforms
- Windows *(64-bit only)*
- ~~Linux~~  *(64-bit only)* *(missing some third-party dependencies)*
- ~~Mac OS X~~ *(64-bit only)* *(missing some third-party dependencies)*

AweSheet will currently compile for all platforms mentioned above, however because of some missing dependencies (will be added soon), you will not be able to run it on any other platform other than Windows.

## Building
Below you will find instructions on how to build AweSheet on all supported platforms.

#### Prerequisites
- JDK 8+
- Node.js 4.0+
- Ant

To build AweSheet please follow the following steps:

```
$ git clone git@github.com:OrfeasZ/AweSheet.git
$ cd AweSheet/src/main/web
$ npm install
$ NODE_ENV=production webpack
$ cd ../../../
$ ant all
```
AweSheet should now be fully compiled. To be able to run it you will have to copy the following files next to the output jar:
```
Depends/bin/** => outputPath/
src/main/web/index.html => outputPath/index.html
src/main/web/static/** => outputPath/static/
```
After doing so, you should be able to run AweSheet by either double-clicking on the output jar or by executing `java -jar AweSheet.jar`.

## Authors
- Orfeas - Ioannis Zafeiris (2250)
- Nikolaos Fylakis (2385)
