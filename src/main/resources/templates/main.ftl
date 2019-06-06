<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row">
    <form method="get" action="/main" class="form-inline">
        <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
        <button type="submit" class="btn btn-primary ml-3" >Найти</button>
    </form>
</div>


<#include "parts/articleEdit.ftl"/>


<#include "parts/articleList.ftl"/>

</@c.page>