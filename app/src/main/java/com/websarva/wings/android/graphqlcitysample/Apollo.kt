package com.websarva.wings.android.graphqlcitysample

import com.apollographql.apollo.ApolloClient

val apolloClient =
    ApolloClient.builder().serverUrl("http://10.0.2.2:9020/query").build()