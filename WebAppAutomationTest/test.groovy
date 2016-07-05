def jobName_1='Parent3'
def gitRepoPath='lokeshlr/Test3'
def gitBranchName='*/master'
freeStylejob('Parent3'){
description('Parent3')
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
