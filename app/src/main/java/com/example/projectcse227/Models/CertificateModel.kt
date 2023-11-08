package com.example.projectcse227.Models

class CertificateModel {
    var certificate_name: String? = null
    var certificate_url: String? = null
    var commitee: String? = null
    var issued_by: String? = null

    constructor()
    constructor(
        certificate_name: String?,
        certificate_url: String?,
        commitee: String?,
        issued_by: String?
    ) {
        this.certificate_name = certificate_name
        this.certificate_url = certificate_url
        this.commitee = commitee
        this.issued_by = issued_by
    }
}