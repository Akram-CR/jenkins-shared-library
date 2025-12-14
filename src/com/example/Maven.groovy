#!/user/bin/env groovy
package com.example

class Maven implements  Serializable {
    def script

    Maven(script) {
        this.script = script
    }

    def MavenBuildPackage(){
        script.sh 'mvn clean package'
    }

}