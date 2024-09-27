#!/bin/bash

sbt -J-Dplay.http.router=testOnlyDoNotUseInAppConf.Routes -Dhttp.port=8960
