package com.mechoori.web.api.entity

import com.mechoori.web.api.entity.enums.Role
import com.querydsl.core.types.Path
import com.querydsl.core.types.PathMetadata
import com.querydsl.core.types.PathMetadataFactory.forProperty
import com.querydsl.core.types.PathMetadataFactory.forVariable
import com.querydsl.core.types.dsl.DateTimePath
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.EnumPath
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import java.lang.Class
import java.util.Date
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.kotlin.codegen.KotlinEntitySerializer")
public class QMember : EntityPathBase<Member> {
  public val email: StringPath = createString("email")

  public val id: NumberPath<Long> = createNumber("id", Long::class.java)

  public val img: StringPath = createString("img")

  public val loginTypeId: NumberPath<Int> = createNumber("loginTypeId", Int::class.java)

  public val nickname: StringPath = createString("nickname")

  public val password: StringPath = createString("password")

  public val regDate: DateTimePath<Date> = createDateTime("regDate", Date::class.java)

  public val role: EnumPath<Role> = createEnum("role", Role::class.java)

  public val roleId: NumberPath<Int> = createNumber("roleId", Int::class.java)

  public val username: StringPath = createString("username")

  public constructor(variable: String) : super(Member::class.java, forVariable(variable))

  public constructor(path: Path<out Member>) : super(path.type, path.metadata)

  public constructor(metadata: PathMetadata) : super(Member::class.java, metadata)

  public constructor(type: Class<out Member>, metadata: PathMetadata) : super(type, metadata)

  public companion object {
    private const val serialVersionUID: Long = 1147617130

    public val member: QMember = QMember("member1")
  }
}
