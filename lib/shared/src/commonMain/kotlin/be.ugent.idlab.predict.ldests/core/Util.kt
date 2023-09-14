package be.ugent.idlab.predict.ldests.core

import be.ugent.idlab.predict.ldests.rdf.NamedNodeTerm
import be.ugent.idlab.predict.ldests.util.warn

/**
 * Divides the given shape into various rules that can be used to create new fragments with, so the
 *  provided `uri` can be used to query over (e.g. constant `ex:prop` with 3 possible values, results in
 *  3 rules dividing `ex:prop` over the three possible values, so up to 3 fragments can be made)
 */
internal fun Shape.split(vararg uris: NamedNodeTerm) = split(uris.toList())

/**
 * Divides the given shape into various rules that can be used to create new fragments with, so the
 *  provided `uri` can be used to query over (e.g. constant `ex:prop` with 3 possible values, results in
 *  3 rules dividing `ex:prop` over the three possible values, so up to 3 fragments can be made)
 */
internal fun Shape.split(uris: Collection<NamedNodeTerm>): List<Stream.Rules> {
    if (uris.isEmpty()) {
        return listOf(
            Stream.Rules(
                constraints = mapOf() /* no constraints */,
                parent = this
            )
        )
    }
    // creating the initial set of rules
    val iter = uris.iterator()
    var uri = iter.next()
    var constraints = (properties[uri] as? Shape.ConstantProperty)?.values?.map {
        mapOf(uri to Shape.ConstantProperty(it))
    } ?: run {
        warn("Unrecognized uri: ${uri.value}")
        emptyList()
    }
    // recursively split all existing fragments from the previous iteration according
    //  to the current iteration's uri
    while (iter.hasNext()) {
        uri = iter.next()
        val prop = properties[uri] as? Shape.ConstantProperty
        if (prop == null) {
            warn("Unrecognized uri: ${uri.value}")
            continue
        }
        constraints = constraints.flatMap { set ->
            prop.values.map { prop ->
                set + mapOf(uri to Shape.ConstantProperty(prop))
            }
        }
    }
    return constraints.map { Stream.Rules(constraints = it, parent = this) }
}
