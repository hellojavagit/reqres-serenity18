package in.reqres.userinfo;

import in.reqres.testbase.TestBase;
import in.reqres.usersinfo.UsersSteps;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {

    static String name = "NewlyCreatedUser" + TestUtils.getRandomValue();
    static String job =  "NewTester" + TestUtils.getRandomValue();
    static String email =  "eve.holt@reqres.in" + TestUtils.getRandomValue();
    static String password =  "cityslicka" + TestUtils.getRandomValue();
    static int id;

    @Steps
    UsersSteps usersSteps;

    @Title("Getting List Of All Users")
    @Test
    public void test001(){
        usersSteps.getListOfAllUsers().statusCode(200);
    }


    @Title("Getting Single User By Id")
    @Test
    public void test002(){
        usersSteps.getUserById(id).statusCode(200);
    }


    @Title("Creating A New User")
    @Test
    public void test003()
    {
        ValidatableResponse response = usersSteps.createUser(name,job);
        response.log().all().statusCode(201);
    }


    @Title("Test4: User Login Successfull")
    @Test
    public void test004()
    {

        ValidatableResponse response = usersSteps.UserLoginSuccessfull(email,password);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the User and verify if the user is deleted or not")
    @Test
    public void test005(){
        usersSteps.deleteUser(id).statusCode(204);
        usersSteps.getUserById(id).statusCode(404);
    }






}
