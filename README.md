Objectives:​ A web-app that mainly focuses on providing users the ability to order
food/ input order, check reservation, and restaurant information through any
web-browser supported device, and portable devices such as tablets and mobiles.

Implementation and technology:
This is a web-app that's heavily based on RESTFUL service. Features such as actively
updating reports of ordered foods, the available foods, and active orders relies on
RESTFUL API as they needs to be and will always be synchronized against the database.
JSON Web Token (JWT) stores the session token for http requests when executing the
RESTFUL services, which is mainly used for authentication and authorization. CSS
framework is not limited to desktops only but also covers mobile css in which fits both
Androids and IOS. In order to support mobile ordering, QR code generator and a
domain will be needed/implemented to accommodate the need of mobile supporting.

