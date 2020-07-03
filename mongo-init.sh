#!/bin/bash
mongo -- "ze-delivery" <<EOF
    db.store.createIndex({coverageArea:"2dsphere"});
    db.store.createIndex( { "document" : 1 }, { unique : true } )
EOF
