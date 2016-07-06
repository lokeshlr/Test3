def jobName_1='Test1'
def gitRepoPath='lokeshlr/Test3'
def gitBranchName='*/master'
freeStyleJob(jobName_1){
description(jobName_1)
scm{
     github(gitRepoPath,gitBranchName)
    }
  steps{
    batchFile('cd %WORKSPACE%\\WebAppAutomationTest'+'\n'+'test.bat')
    dsl{external('**/test.groovy')}
  }
  triggers {
        scm('*/5 * * * *')
        githubPush()
    }
	publishers {
        artifactDeployer {
            artifactsToDeploy {
                includes('**/test-output/index.html')
                remoteFileLocation('D:\\PennyMac\\Archieve\\BUILD_ID_${BUILD_ID}')
                deleteRemoteArtifacts()
            }
        }
    }
}
