/* 
 * ====================
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2013 ForgeRock. All rights reserved.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License("CDDL") (the "License").  You may not use this file
 * except in compliance with the License.
 * 
 * You can obtain a copy of the License at
 * http://opensource.org/licenses/cddl1.php
 * See the License for the specific language governing permissions and limitations
 * under the License.
 * 
 * When distributing the Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://opensource.org/licenses/cddl1.php.
 * If applicable, add the following below this CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * ====================
 * Portions Copyrighted 2013 ConnId.
 */
import groovy.sql.Sql
import groovy.sql.DataSet
import com.rabbitmq.client.*

// Parameters:
// The connector sends the following:
// connection: handler to the SQL connection
// action: a string describing the action ("TEST" here)
// log: a handler to the Log facility

log.info("Entering "+action+" Script")
def sql = new Sql(connection)

sql.eachRow("select * from grouper_members limit 10", { println it.subject_id } )

factory = new ConnectionFactory()
factory.host = 'mq'
factory.port = 5672
connection = factory.newConnection()
channel = connection.createChannel()
println 'conn=' + connection + ', channel=' + channel

channel.close()
connection.close()
