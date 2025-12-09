#!/user/bin/env groovy

def call(String imageName) {
    echo "building the docker image from branch name $BRANCH_NAME"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        // sh 'docker build -t crakram/test-repo:jma-2.0 .'
        sh "docker build -t $imageName ."

        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push $imageName"
    }
}
