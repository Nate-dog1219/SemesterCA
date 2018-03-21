

package controllers;

import controllers.routes;
import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.productAdmin.*;
import models.users.*;

public class RegisterUser extends Controller{

    private FormFactory formFactory;
    private Environment environment;

    @Inject
    public RegisterUser(FormFactory formFactory, Environment environment) {
        this.formFactory = formFactory;
        this.environment = environment;
    }

    public Result register() {
        Form<User> userForm = formFactory.form(User.class);
        return ok(registerUser.render(userForm, User.getUserById(session().get("email"))));
    }

    @Transactional
    public Result registrationSubmit() {
        User newUser;
        Form<User> newUserForm = formFactory.form(User.class).bindFromRequest();
        newUser = newUserForm.get();

        if(newUserForm.field("confirmPassword").getValue().isPresent()) {
            if (!newUser.getPassword().equals(newUserForm.field("confirmPassword").getValue().get())) {
                newUser.setPassword("");
                Form<User> userForm = formFactory.form(User.class).fill(newUser);
                return ok(registerUser.render(userForm, User.getUserById(session().get("email"))));
            }
        } else {
            return badRequest();
        }
        if (newUserForm.hasErrors()){
            return badRequest(registerUser.render(newUserForm, User.getUserById(session().get("email"))));
        }
        else {
            newUser.save();
        }
        flash("success", "User " + newUser.getName() + " has been created");

        if(newUser.getClass().equals(Administrator.class)) {

        }
        return redirect(controllers.routes.ProductCtrl.index());
    }


}
