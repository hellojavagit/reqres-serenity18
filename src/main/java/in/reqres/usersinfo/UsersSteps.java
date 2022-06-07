package in.reqres.usersinfo;

import in.reqres.constants.EndPoints;
import in.reqres.model.UsersPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UsersSteps {

    @Step("Getting Users information ")
    public ValidatableResponse getListOfAllUsers() {
        return SerenityRest.given().log().all()
                .queryParam("page", 2)
                .when()
                .get(EndPoints.LIST_ALL_USERS)
                .then();
    }

    @Step("Getting User information with UserId: {0}")

    public ValidatableResponse getUserById(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", 2)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();

    }

    @Step("Creating a User with name:{0},job :{1}")
    public ValidatableResponse createUser(String name, String job) {
        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setName(name);
        usersPojo.setJob(job);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(usersPojo)
                .when()
                .post(EndPoints.CREATE_SINGLE_USER)
                .then();
    }

    @Step("User Should Login successfully With Email And Password")
    public ValidatableResponse UserLoginSuccessfull(String email, String password) {

        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setEmail(email);
        usersPojo.setPassword(password);
        return SerenityRest.given().log().all()
                //.contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(usersPojo)
                .when()
                .post(EndPoints.USER_LOGIN)
                .then();
    }

    @Step("Deleting User information with UserId: {0}")
    public ValidatableResponse deleteUser(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();

    }

}

