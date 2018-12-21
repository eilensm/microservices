pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(maven: 'M3') {
                    sh "mvn clean install"
                }
            }
        }

        stage('PIT Mutation Tests') {
            steps {
                withMaven(maven: 'M3') {
                    sh 'mvn org.pitest:pitest-maven:mutationCoverage'
                }
                step([
                    $class: 'PitPublisher',
                    mutationStatsFile: '**/mutations.xml',
                    minimumKillRatio: 00.00,
                    killRatioMustImprove: false
                ])
            }
        }
    }
}
