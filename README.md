# NavGraphCheckerPlugin
![NavGraphCheckerPlugin](./assets/NavGraphCheckerPlugin.png)

## What's NavGraphCheckerPlugin?
NavGraphCheckerPlugin is a gradle plugin for checking NavGraph correctness at compile time.

Even when there are some mistakes in navigation graph, you can compile and build your project. Then you will notice the mistake only when you launch a screen which use the problematic navigation graph.

![](./assets/wrong_destination.png)

<img src="./assets/wrong_nav.gif" width=300px>

## How to use
```build.gradle
buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()

    }
    dependencies {
        classpath "io.moatwel.android.plugin.navigation-tools:0.1.0-alpha"
    }
}
```

```build.gradle
apply plugin: 'com.android.application'
apply plugin: 'io.moatwel.android.plugin.navigation-tools'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    ...
}
```

When you wanna check NavGraph, run below command.
```shell
$./gradlew checkNavGraph
```

If there is some error in NavGraph, a below error has come.

```shell
Execution failed for task ':sample:checkNavGraph'.
> Error in NavGraph. [navigation_third_screen.xml, navigation_second_screen.xml]
```

in Android Studio, error shown like below.

![](./assets/build_error.png)

## Author
- Yasunori Horii
  - GitHub (https://github.com/halu5071)
  - Twitter (https://twitter.com/halu5071)

## License
```
Copyright 2020 Yasunori Horii.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
