<#include "secure.ftl">
<div class="card-columns">
    <#list articles as article>
        <div class="card my-3">
            <#if article.filename??>
                <img src="/img/${article.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${article.text}</span><br/>
                <i>#${article.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-articles/${article.avtor.id}"> ${article.avtorName}</a>
                <#if article.avtor.id == currentUserId>
                    <a class="btn btn-primary" href="/user-articles/${article.avtor.id}?article=${article.id}">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>