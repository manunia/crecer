<#import "parts/common.ftl" as c>

<@c.page>
<h2>${userChannel.username}</h2>
<#if !isCurrentUser>
    <#if isSubscriber>
        <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">unSubscribe</a>
    <#else>
        <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Subscribe</a>
    </#if>
</#if>
<div class="container my-3">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Подписки</div>
                    <div class="card-text">
                        <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Подписчики</div>
                    <div class="card-text">
                        <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#if isCurrentUser>
    <#include "parts/articleEdit.ftl"/>
</#if>
<#include "parts/articleList.ftl"/>
</@c.page>