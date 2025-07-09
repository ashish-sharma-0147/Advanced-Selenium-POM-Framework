pipeline {
  agent {
    label 'stage-linux-worker'
    }
        parameters {
        	//gitParameter(branch: '', branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'Branch', quickFilterEnabled: true, requiredParameter: true, selectedValue: 'DEFAULT', sortMode: 'NONE', tagFilter: '*', type: 'GitParameterDefinition')
            string(defaultValue: 'main', description: '''<em><Strong> Please enter the branch name </Strong></em>''', name: 'Branch', trim: true)
        	
            activeChoice choiceType: 'PT_SINGLE_SELECT',
            description: '<em><Strong> Select the Environment which you want to test </Strong></em>',
            filterLength: 1,
            filterable: false,
            name: 'Select_Environment',
            randomName: 'choice-parameter-4480111157512635',
            script: groovyScript(fallbackScript: [classpath: [], oldScript: '', sandbox: true, script: 'return[\'error\']'],
              script: [
                classpath: [],
                oldScript: '',
                sandbox: true,
                script: '''return [\'https://frost-dev.health.elsevier.com/\':\'DEV\',
                                            \'https://frost-cert.health.elsevier.com/\':\'CERT:selected\',
                                            \'https://frost-stage.health.elsevier.com/\':\'UAT\',
                                            \'https://frost-prod.health.elsevier.com/\':\'PROD\']'''
              ])
              
            validatingString(defaultValue: '', description: '<em><Strong> Please enter the username </Strong></em>', failedValidationMessage: 'Username should have minimum of 2 characters excluding whitespace!!  ', name: 'Frost_Username', regex: '^\\S{2,}$')
            //validatingString(defaultValue: '', description: '<Strong><em> Please enter the encrypted password </em></Strong>', failedValidationMessage: 'Password should have minimum of 2 characters excluding whitespace!!  ', name: 'Password', regex: '^\\S{2,}$')
            password(defaultValue: '', description: '<Strong><em> Please enter the encrypted password. </em></Strong>', name: 'Frost_Password') 
              
            activeChoice choiceType: 'PT_SINGLE_SELECT',
            description: '<Strong> Select the browser on which you want to run test </Strong>',
            filterLength: 1,
            filterable: false,
            name: 'Select_Browser',
            randomName: 'choice-parameter-4479594342235803',
            script: groovyScript(fallbackScript: [classpath: [], oldScript: '', sandbox: true, script: 'return[\'error\']'],
              script: [
                classpath: [],
                oldScript: '',
                sandbox: true,
                script: "return [\'Chrome\',\'FireFox:selected\',\'Edge:disabled\',\'Safari:disabled\']"
              ])
              
            string description: '''<em><Strong> Select the test group/name you want to run </Strong></em><br>
                                <p1> Leave this option blank if you want to run all tests (Full Regression) </p1>''', 
                name: 'Select_Group'
            
            activeChoice choiceType: 'PT_SINGLE_SELECT', 
            description: '''<Strong> Select \'Yes\' If you want to run test cases in Parallel Mode </Strong><br>''', 
            filterLength: 1, 
            filterable: false, 
            name: 'Parallel_Execution', 
            randomName: 'choice-parameter-4538679921205622', 
            script: groovyScript(fallbackScript: [classpath: [], oldScript: '', sandbox: true, script: 'return[\'error\']'], 
                    script: [
                        classpath: [], 
                        oldScript: '', 
                        sandbox: true, 
                        script: 'return[\'Yes\',\'No:selected\']'
                        ])
            
            reactiveChoice choiceType: 'PT_SINGLE_SELECT', 
            description: '''<Strong> Please select the Parallel Execution Mode </Strong> <br> <em> Kindly select <Strong>\'CLASSES\'</Strong> for efficient execution </em>''', 
            filterLength: 1, 
            filterable: false, 
            name: 'Parallel_Execution_Mode', 
            randomName: 'choice-parameter-4539483810506355', 
            referencedParameters: 'Parallel_Execution', 
            script: groovyScript(fallbackScript: [classpath: [], oldScript: '', sandbox: true, script: 'return[\'error\']'], 
                    script: [ 
                        classpath: [], 
                        oldScript: '', 
                        sandbox: true, 
                        script: '''if(Parallel_Execution.equals(\'No\')){return[\'NONE:disabled\']}else if(Parallel_Execution.equals(\'Yes\')){return[\'CLASSES:selected\',\'METHODS\',\'INSTANCES\',\'TESTS\',\'NONE\']}'''
                        ])
                        
            reactiveChoice choiceType: 'PT_SINGLE_SELECT', 
            description: '''<Strong> Please select the Parallel Execution Thread Count </Strong> <br><em> Kindly select <Strong>\'2\'</Strong> threads for efficient execution </em>''', 
            filterLength: 1, 
            filterable: false, 
            name: 'Parallel_Execution_Thread_Count', 
            randomName: 'choice-parameter-4539754751914992', 
            referencedParameters: 'Parallel_Execution', 
            script: groovyScript(fallbackScript: [classpath: [], oldScript: '', sandbox: true, script: 'return[\'error\']'], 
                    script: [
                        classpath: [], 
                        oldScript: '', 
                        sandbox: true, 
                        script: '''if(Parallel_Execution.equals(\'No\')){return[\'1:disabled\']}else if(Parallel_Execution.equals(\'Yes\')){return[\'1\':\'1\',\'2:selected\':\'2\',\'3\':\'3\',\'4\':\'4:disabled\',\'5\':'5:disabled\',\'11\':\'MAX\']}'''
                        ])
        }
        stages {
          //stage('Get Source') {
            //steps {
            //  git branch: '$Branch', credentialsId: 'github-ssh-key', url: 'git@github.com:elsevier-health/hcm-digital-authoring-automated-testing.git'
           // }
          //}
          
          stage('Build Code') {
            steps {
              sh 'mvn compile'
            }
          }
          
          stage('Test Execution') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE'){
                    sh 'mvn test -Dapplication.url=$Select_Environment -Dusername=$Frost_Username -Dpassword=$Frost_Password -Dbrowser=$Select_Browser -Dgroups=$Select_Group -Dparallel.execution.mode=$Parallel_Execution_Mode -Dparallel.execution.thread.count=$Parallel_Execution_Thread_Count'
                }
            }
          }
          
          stage('Generate Reports') {
            steps {
              publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: '', reportFiles: 'TestReports/*.html', reportName: 'Extent_Report', reportTitles: '', useWrapperFileDirectly: true])
              testNG(reportFilenamePattern: '**/target/surefire-reports/testng-results.xml')
              //seleniumHtmlReport(String testResultLocation = 'TestReports/*.html') {}
            }
          }
        }
      }