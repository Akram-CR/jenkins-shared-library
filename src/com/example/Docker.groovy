#!/user/bin/env groovy
package com.example

class Docker implements  Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image from branch name ${script.env.BRANCH_NAME}"
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            // sh 'docker build -t crakram/test-repo:jma-2.0 .'
            script.sh "docker build -t $imageName ."

            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
            script.sh "docker push $imageName"
        }

    }
}