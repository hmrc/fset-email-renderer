# fset-email-renderer

[![Build Status](https://travis-ci.org/hmrc/fset-email-renderer.svg?branch=master)](https://travis-ci.org/hmrc/fset-email-renderer) [ ![Download](https://api.bintray.com/packages/hmrc/releases/fset-email-renderer/images/download.svg) ](https://bintray.com/hmrc/releases/fset-email-renderer/_latestVersion)

# Fast Stream & Early Talent Email Templates

This repository holds the HTML and plain text email templates for the Fast Stream and Fast Track programmes.
Note that any sensitive URLs or values should not be checked into these templates.  Please inject these parameters via app config to the paramter map used by the templates.

**Note: Before sending a pull request with template changes, please look at our [guidelines](/CONTRIBUTING.md).**


## API

| Path                         | Supported Methods | Description  |
| ---------------------------- | ----------------  | ------------ |
| ```/templates/:templateId``` | POST              | Renders the email template for the given template Id [More...](#post-templatestemplateId) |


### POST /templates/:templateId

Renders the email template for the  given template Id and returns the rendered template details.

Example request body - parameters with String type key and values.

```json
{
  "parameters": {
    "param1" : "Parameter to be used in the email template"
    "param2" : "Parameter to be used in the email template"
  }
}
```

Responds with status:

* 200 When the template is rendered successfully

 ```json
{
    "plain": "Rendered template in text format",
    "html": "Rendered template in HTML format",
    "fromAddress": "From address to be used for this email when sending",
    "subject": "Email subject to use",
    "service": "The regime (i.e. - sa/paye/etc) that this template belongs to"
}
 ```
* 404 When the template with the provided template Id cannot be resolved.

* 400 When an insufficient set of parameters for rendering the template is specified in the request. Only the first missing value is reported.

 ```json
{
    "status": "Rendering of template failed",
    "reason": "key not found: param10"
}
 ```

### Preview Mode

Templates can be fiddly to get right as they can ordinarily only be viewed by actually generating an email and sending it. To work around this you can preview templates **during development** by running the micro-service from sbt:

```bash
cd $WORKSPACE/fset-email-renderer
sbt -Dhttp.port=8960 -Dapplication.router=testOnlyDoNotUseInAppConf.Routes run
```

You should be able to list all the templates available for preview from [http://localhost:8960/fset-email-renderer/test-only/preview](http://localhost:8960/fset-email-renderer/test-only/preview).

Note that to render logos correctly the assets frontend also needs to be started using

```bash
sm --start ASSETS_FRONTEND -f
```

**You must set `programme` in the query string, when using preview feature eg. `programme=faststream`**

You may also send template parameters in the query string as key=value pairs.

#### Quick Preview
Alternatively, you can do a preview of emails by starting the service using `sm2` to preview the source, snapshot or release versions of the micro-service.

```bash
sm2 --start ASSETS_FRONTEND -f
sm2 --start FSET_EMAIL_RENDERER [-f|-r]
```

Again, list the templates can then be previewed from

[http://localhost:8960/fset-email-renderer/test-only/preview](http://localhost:8960/fset-email-renderer/test-only/preview)

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").


