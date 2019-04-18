# SystemsAnalysisDesignProject

## Git Workflow
I will define the git workflow here for the sake of visibility.

### Branches

First ensure that we are up to date with master.
```console
git checkout master
git pull origin master
```

Create the new local branch. Note the naming convention. First the username of the person creating the branch and second a word or two to describe the branch. For example jamiemac96-android_login
```console
git checkout -b username-feature_description
```

Once the branch is created locally we can start making changes.

When we have made our changes and are ready to merge back into master 
we must ensure that master is up to date. 

```console
git checkout master
git pull
```

Now we must merge master into our feature branch, in order to bring it up to 
date with any changes made in master since we created the new branch.

```console
git checkout feature-branch
git merge master
```

If there are any merge conflicts we resolve them and merge again.

Finally we push our local feature branch to github.

```console
git push origin feature-branch
```

After the feature branch has been added to github we can the make a 
pull request as well as request a code review.

When atleast one person has reviewed the code and any necessary 
changes have been made then we can merge the new changes into master.

## Interacting with the API

When sending a http request to the api, a json response will be returned. This response will contain a http code, a generic message and an optional extended message. Responses will look as follows:

```
{"code":404,"msg":"Failure.","extend":{"error":"Bike or User does not exist."}}
```

### Register User
To register a new user, send a http POST request with the parameters username, password, email, userTypeId and optionally studentCardId to the "/user" path. If no studentCardId is provided value will default to null. Example request:

```
curl -X POST -d "username=roryegan&password=password&userTypeId=1&email=rory@gmail.com" http://localhost:1234/user
```

If register is successful the http response code will be 200. If an existing username is already in the database the code will be 400 and if any other error such as non-existing userTypeId or studentCardId are provided the code will be 400.

### Login User

To login an existing user, send a PUT request with username and password as parameters to the "/user" path:
```
curl -X PUT -d "username=roryegan&password=password" http://localhost:1234/user
```

If login is successful the standard 200 code will be returned. If password is incorrect 400 will be returned. If username is not present in the database 404 will be returned.

### Create Bike

To create a new bike, send a POST request to "/bike". Parameters are bikeType and nodeId. As new bikes can only be added to nodes, position defaults to null, as does lastUserId. Bike is automatically available upon creation. Example:
```
curl -X POST -d "bikeType=1&nodeId=1" http://localhost:1234/bike
```

200 code should be returned upon success, otherwise a generic error code as there is no danger of conflicts.

### Rent Bike

To place a new order, send a POST request to "/rent". Parameters required are id (of the bike to be rented), and userId. Example:
```
curl -X POST -d "id=5&userId=28" http://localhost:1234/rent
```

If successful, code 200 will be returned along with an orderId, if bike or user do not exist 404 will be returned and for any other errors 400 will be returned. Make sure orderId is stored as it is required to return the bike.

### Return Bike

To return a bike send a POST request to "return". Required parameters are userId and studentCardId. Optionally return a nodeId or latitude and longitude values. Whichever is not passed will be set to null. Either nodeId or latitude and longitude must be returned, if nothing passed will raise an error, so pass in either one or the other. Examples:
```
curl -X POST -d "userId=13&studentCardId=115&latitude=1&longitude=1" http://localhost:1234/return
curl -X POST -d "userId=27&studentCardId=1122&nodeId=1" http://localhost:1234/return
```

## API

* login `PUT /user`   Body required: username, password
* register `POST /user` Body required: username, password, studentCardId, userTypeId, email
* nodes `GET /node`
* bikeByNodes `GET /node/{nodeId}`
* createBike `POST /bike` Body required: bikeType, nodeId
* **ABORTED** ~~setStatus `PUT /bike/{bikeId}` Body required: status~~
* reportBike `POST /report/{bikeId}` Body required: userId, reportText
* reports `GET /reports`
* rent `POST /rent` Body required: id(bikeId), userId
* reductions `GET /money/{minutes}`
* return `POST /return` Body required: userId, studentCardId AND: latitude&longitude OR nodeId
* **ABORTED** ~~playOrder `POST /order` Body required: userName, bikeId, amountPaid~~
