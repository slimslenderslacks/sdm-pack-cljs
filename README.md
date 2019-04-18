# Purpose

Demonstrate a typescript CodeTransform that delegates to clojurescript code to transform sexps.  

This is a mixed project containing both typescript and clojurescript.

## Requirements

1. Install leiningen
2. Install node.js
3. Install the atomist cli

```
npm install -g @atomist/cli
```

## Build

```
npm run compile
```

* compiles clojurescript part first (in `src`)
* then compiles typesript part (in `lib`)

End result is something that could be packaged and distributed using `npm publish`.  

## Run locally

```
atomist start
```

This implies that you have already configured a workspace using [these instructions](https://docs.atomist.com/quick-start/).  As long as you have an Atomist workspace configured, the above command will add a new `CodeTransform` to your workspace.  If you have a Slack bot installed and a leiningen project Repo linked to a Channel then go switch to that channel and:

```
@atomist cljs name=org.clojure/clojure version=x.y.z
```

This will generate a PullRequest updating a dependency in your project.clj file.  The cljs code is [here](https://github.com/slimslenderslacks/sdm-pack-cljs/blob/master/src/cljs/slimslender/main.cljs).