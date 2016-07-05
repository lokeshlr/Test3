def jobName_1='Parent2'
def gitRepoPath='lokeshlr/Test3'
def gitBranchName='*/master'
freeStylejob(jobName_1){
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
