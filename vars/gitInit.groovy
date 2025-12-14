#!/user/bin/env groovy

import com.example.Git

def call(String gitCredentialsId, String gitProjectUrl){
    return new Git(this).gitInitConfig(gitCredentialsId, gitProjectUrl)
}
