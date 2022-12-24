package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class StepsDefinition {

    private static final String BASE_URL = "https://reqres.in";

    private static String path;
    private static Response response;
    private static String jsonString;
    private static String bookId;

    @Given("Se tiene el endpoint {string}")
    public void seTieneElEndpoint(String endpoint) {
        RestAssured.baseURI = BASE_URL;

        path = endpoint;
        System.out.println("La ruta es:"+BASE_URL+path);
    }

    @When("se realiza la peticion List get")
    public void seRealizaLaPeticionListUser() {
        RequestSpecification request = RestAssured.given();
        response = request.get(path);
    }

    @When("se realiza la peticion post con {string} y {string}")
    public void seRealizaLaPeticionPost(String name, String job) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}")
                .post(path);

    }

    @When("se realiza la peticion put con {string} y {string}")
    public void seRealizaLaPeticionPutConY(String name, String job) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"name\": \""+name+"\",\n" +
                        "    \"job\": \""+job+"\"\n" +
                        "}")
                .put(path);

    }

    @When("se realiza la peticion patch con {string} y {string}")
    public void seRealizaLaPeticionPatchConY(String name, String job) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"name\": \""+name+"\",\n" +
                        "    \"job\": \""+job+"\"\n" +
                        "}")
                .patch(path);

    }

    @When("se realiza la peticion delete")
    public void seRealizaLaPeticionDelete() {
        RequestSpecification request = RestAssured.given();
        response = request.delete(path);
    }

    @When("se realiza el registro con {string} y {string}")
    public void seRealizaElRegistroConY(String email, String pass) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"email\": \""+email+"\",\n" +
                        "    \"password\": \""+pass+"\"\n" +
                        "}")
                .post(path);
    }

    @Then("se visualiza la respuesta con los datos {string} y {string}")
    public void seVisualizaLaRespuestaConLosDatosY(String name, String job) {
        String jsonString = response.asString();

        String name1 = JsonPath.from(jsonString).get("name");
        String job1 = JsonPath.from(jsonString).get("job");

        System.out.println("Se obtuvo como nombre: "+name1);
        System.out.println("Se obtuvo como job: "+job1);

        Assert.assertEquals(name,name1);
        Assert.assertEquals(job,job1);

    }

    @When("se realiza el login con {string} y {string}")
    public void seRealizaElLoginConY(String email, String pass) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"email\": \""+email+"\",\n" +
                        "    \"password\": \""+pass+"\"\n" +
                        "}")
                .post(path);
    }

    @Then("Se visualiza el error {string}")
    public void seVisualizaElError(String error) {
        jsonString = response.asString();
        Assert.assertEquals(error,JsonPath.from(jsonString).get("error"));
    }

    @Then("el status de la respuesta debe ser {int}")
    public void elStatusDeLaRespuestaDebeSer(int Status) {

        jsonString = response.asString();
        System.out.println("El response es:"+jsonString);
        Assert.assertEquals(Status,response.getStatusCode());
    }



}
