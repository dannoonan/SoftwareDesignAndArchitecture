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

### Place Order

To place a new order, send a POST request to "/order". Parameters required are username, bikeId and amountPaid. Example:
```
curl -X POST -d "userName=roryegan&bikeId=3&amountPaid=100" http://localhost:1234/order
```

If successful, code 200 will be returned, if bike or user do not exist 404 will be returned and for any other errors 400 will be returned.

## API

* login `PUT /user`   Body required: username, password
* register `POST /user` Body required: username, password, studentCardId, userTypeId, email
* nodes `GET /node`
* bikeByNodes `GET /node/{nodeId}`
* creatBike `POST /bike` Body required: bikeType, nodeId, position (ps: position format would be string for instance 120, 129)
* **ABORTED** ~~setStatus `PUT /bike/{bikeId}` Body required: status~~
* reportBike `POST /report/{bikeId}` Body required: userId, reportText
* reports `GET /reports`
* rent `POST /rent/{bikeId}` Body required: userId
* reductions `GET /money/{minutes}`
* return `POST /return` Body required: orderId, latitude, longitude, amountPaid, studentCardId, nodeId
* **ABORTED** ~~playOrder `POST /order` Body required: userName, bikeId, amountPaid~~
