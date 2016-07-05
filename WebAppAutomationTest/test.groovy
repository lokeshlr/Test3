def jobName_1=%JOB_NAME%
def gitRepoPath='lokeshlr/Test3'
def gitBranchName='*/master'
job(jobName_1){
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