#!/user/bin/env groovy
package com.example

class Git implements  Serializable {
    def script

    Git(script) {
        this.script = script
    }

    def gitInitConfig(String gitCredentialsId, String gitProjectUrl) {
        script.withCredentials([script.usernamePassword(credentialsId: gitCredentialsId, passwordVariable: 'GIT_PASS', usernameVariable: 'GIT_USER')]) {
            def safePass = java.net.URLEncoder.encode(script.GIT_PASS, "UTF-8")
            script.sh 'git config --global user.email "jenkins@example.com"'
            script.sh 'git config --global user.name "jenkins"'
            script.sh 'git status'
            script.sh 'git branch'
            script.sh 'git config --list'

            script.sh "git remote set-url origin https://${script.GIT_USER}:${safePass}@$gitProjectUrl"
            //script.sh "git remote set-url origin https://${script.GIT_USER}:${safePass}@gitlab.com/akram.cheikhrouhou2013/java-maven-app-git.git"
        }
    }

    def gitPush(String versionNumber, String branchName) {
        script.sh 'git add .'
        script.sh 'git commit -m "ci: commit version $versionNumber"'
        script.sh 'git push origin HEAD:$branchName'
    }

}