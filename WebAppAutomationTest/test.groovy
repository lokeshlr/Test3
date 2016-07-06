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
  }
  triggers {
        scm('*/5 * * * *')
        githubPush()
    }
}
