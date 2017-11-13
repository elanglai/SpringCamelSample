node {
    def buildInfo

    stage('Checkout') {
        checkout([$class: 'SubversionSCM',
            additionalCredentials: [],
            excludedCommitMessages: '',
            excludedRegions: '',
            excludedRevprop: '',
            excludedUsers: '',
            filterChangelog: false,
            ignoreDirPropChanges: false,
            includedRegions: '',
            locations: [[credentialsId: '307a68ab-fd97-46e3-8e9e-4e9b783d71f3',
                depthOption: 'infinity',
                ignoreExternalsOption: true,
                local: '.',
                remote: 'https://perseus.teksystems.com/svn/TRP_MiFID']],
                workspaceUpdater: [$class: 'UpdateUpdater']])
    }

    stage('Maven build') {
        buildInfo = rtMaven.run pom: 'pom.xml', goals: 'clean package'
    }

    stage('Archive artifacts' {
        archiveArtifacts '*.jar'
    }
}