<#import "parts/common.ftl" as c>

<@c.page>
<h5>${username}</h5>
${message?ifExists}
<form method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-5">
            <input class="form-control" type="password" name="password" placeholder="Enter password"/>
        </div>

    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Save</button>
</form>
</@c.page>