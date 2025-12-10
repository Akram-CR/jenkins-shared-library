#!/user/bin/env groovy

def call() {
    echo "building the application from branch name ${env.BRANCH_NAME}"
    sh 'mvn package'
}
