## IMPORTANT!!!

#### KEY
Github API requires authorization with token.
This Application gets token from GITHUB_KEY environment variable during build time and puts it to
the BuildConfig.GithubKey variable, where RetrofitProvider gets it.

**<span style="color:red">
Before you build the app set the key with one of two ways:    
</span>**

* Set GITHUB_KEY environment wariable with the valid github token    
  or    
* Hardcode your key directly in app\build.gradle.kts (line 36)    
  or    
* Hardcode your key directly in RetrofitProvider.kt



If you build the App without key it will still work. But after a few requests quota will run out and
API will return no data.


#### PAGINATION & STRING RES
There are 2 features that I have "postponed". String resources: yes, I know that we should use it,
but for the sake of demo purposes, I have saved myself some time and gave up on it.
I have also prepared repository for pagination, but I had no time to implement it.
