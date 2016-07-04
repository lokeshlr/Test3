job('test31'){
steps{
batchFile(readFileFromWorkspace('test.bat'))
}
}
