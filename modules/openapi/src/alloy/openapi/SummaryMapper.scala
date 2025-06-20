/* Copyright 2022 Disney Streaming
 *
 * Licensed under the Tomorrow Open Source Technology License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://disneystreaming.github.io/TOST-1.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package alloy.openapi

import software.amazon.smithy.model.shapes.OperationShape
import software.amazon.smithy.model.traits.Trait
import software.amazon.smithy.openapi.fromsmithy.{Context, OpenApiMapper}
import software.amazon.smithy.openapi.model.OperationObject

class SummaryMapper() extends OpenApiMapper {

  override def updateOperation(
      context: Context[_ <: Trait],
      shape: OperationShape,
      operation: OperationObject,
      httpMethodName: String,
      path: String
  ): OperationObject = {
    if (shape.hasTrait(classOf[SummaryTrait])) {
      val summary = shape.expectTrait(classOf[SummaryTrait]).getSummary
      operation.toBuilder
        .summary(summary)
        .build()
    } else {
      operation
    }
  }
}
