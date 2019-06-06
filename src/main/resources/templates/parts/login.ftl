<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name :</label>
        <div class="col-sm-5">
            <input class="form-control ${(usernameError??)?string('is-invalid', '')}" type="text" name="username" value="<#if user??>${user.username}</#if>" placeholder="Enter youre name"/>
            <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
            </#if>
        </div>

    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-5">
            <input class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" name="password" placeholder="Enter password"/>
            <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
        </#if>
        </div>

    </div>

    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-5">
            <input class="form-control ${(password2Error??)?string('is-invalid', '')}" type="password" name="password2" placeholder="Confirm password"/>
            <#if password2Error??>
            <div class="invalid-feedback">
                ${password2Error}
            </div>
            </#if>
        </div>

    </div>
    </#if>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit"><#if user??>Sign Out<#else>LogIn</#if></button>
</form>
</#macro>