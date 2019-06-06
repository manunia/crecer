<a class="btn btn-primary mt-4" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Editor
</a>
<div>
    <div class="collapse <#if article??>sshow</#if>" id="collapseExample">
        <div class="form-group mt-4">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-control ${(textError??)?string('is-invalid', '')}" value="<#if article??>${article.text}</#if>" type="text" name="text" placeholder="Введите сообщение" />
                    <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
        </div>
        <div class="form-group">
            <input class="form-control ${(tagError??)?string('is-invalid', '')}" type="text" value="<#if article??>${article.tag}</#if>" name="tag" placeholder="Тэг">
            <#if tagError??>
            <div class="invalid-feedback">
                ${tagError}
            </div>
        </#if>
    </div>
    <div class="form-group">
        <div class="custom-file">
            <input type="file" name="file" id="customFile">
            <label class="custom-file-label" for="customFile">Add file</label>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="hidden" name="id" value="<#if article??>${article.id}</#if>" />
    <div class="form-group">
        <button class="btn btn-primary ml-3" type="submit">Save</button>
    </div>
    </form>
</div>
</div>
</div>
