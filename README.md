# LDESTS demo
This repository is an adapter version of the [LDES-in-SOLID-Semantic-Observations-Replay](https://github.com/SolidLabResearch/LDES-in-SOLID-Semantic-Observations-Replay) project, which originally uses LDES-in-LDP to store the replayed data points into the configured Solid pod. The configuration file has been adapted to provide an LDESTS data model, and uses the `ldests` dependency found on NPM to demo the project. The original project's README stating the goal of this tool is also present in this repository, called [README_observations_replay](README_observations_replay.md). 
## Demonstration
A demo using a subset of the [DAHCC](https://dahcc.idlab.ugent.be/dataset.html) dataset is used for demonstration purposes. This subset only contains the accelerometer data captured by smartphones. The shape had been adjusted for this specific subset, and the entire capture was published.

[Click here to open the video in a new tab](https://github-production-user-asset-6210df.s3.amazonaws.com/57372186/253955553-d8cf225e-a8fd-4e26-9fcf-493c97c45bb7.mp4)

## Trying the demo yourself
### Installation
You need to clone the repository and install the appropriate dependencies for both `engine` and `webapp` to test the project for yourself.
```
[user@host ~]$ git clone https://github.com/SolidLabResearch/LDESTS-demo
[user@host ~]$ cd LDESTS-demo/{engine,webapp}
[user@host {engine,webapp}]$ npm i
```
### Running
You need two terminal instances to run the demo: one for the frontend, and one for the underlying engine managing the data and using LDESTS. Navigate the first terminal window to `demo/engine` and start the engine:
```
[user@host LDESTS-demo]$ cd demo/engine
[user@host engine]$ npm start
```
Next, navigate the second terminal to `demo/webapp` and start the frontend:
```
[user@host LDESTS-demo]$ cd demo/webapp
[user@host webapp]$ npm run dev
```
This terminal should now contain the URL you can navigate to when you want to access the frontend and start testing the demo.
