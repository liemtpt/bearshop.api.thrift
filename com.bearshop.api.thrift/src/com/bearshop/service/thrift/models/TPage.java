/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.bear.shop.service.api.thrift.models;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TPage implements org.apache.thrift.TBase<TPage, TPage._Fields>, java.io.Serializable, Cloneable, Comparable<TPage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TPage");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PARENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("parentId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField LINK_FIELD_DESC = new org.apache.thrift.protocol.TField("link", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField LINK_ALIAS_FIELD_DESC = new org.apache.thrift.protocol.TField("linkAlias", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField ORDERING_FIELD_DESC = new org.apache.thrift.protocol.TField("ordering", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField DATE_CREATED_FIELD_DESC = new org.apache.thrift.protocol.TField("dateCreated", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField DATE_MODIFIED_FIELD_DESC = new org.apache.thrift.protocol.TField("dateModified", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TPageStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TPageTupleSchemeFactory());
  }

  public int id; // required
  public int parentId; // required
  public String name; // required
  public String link; // required
  public String linkAlias; // required
  public int ordering; // required
  public String dateCreated; // required
  public String dateModified; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    PARENT_ID((short)2, "parentId"),
    NAME((short)3, "name"),
    LINK((short)4, "link"),
    LINK_ALIAS((short)5, "linkAlias"),
    ORDERING((short)6, "ordering"),
    DATE_CREATED((short)7, "dateCreated"),
    DATE_MODIFIED((short)8, "dateModified");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // PARENT_ID
          return PARENT_ID;
        case 3: // NAME
          return NAME;
        case 4: // LINK
          return LINK;
        case 5: // LINK_ALIAS
          return LINK_ALIAS;
        case 6: // ORDERING
          return ORDERING;
        case 7: // DATE_CREATED
          return DATE_CREATED;
        case 8: // DATE_MODIFIED
          return DATE_MODIFIED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __PARENTID_ISSET_ID = 1;
  private static final int __ORDERING_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PARENT_ID, new org.apache.thrift.meta_data.FieldMetaData("parentId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LINK, new org.apache.thrift.meta_data.FieldMetaData("link", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LINK_ALIAS, new org.apache.thrift.meta_data.FieldMetaData("linkAlias", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORDERING, new org.apache.thrift.meta_data.FieldMetaData("ordering", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DATE_CREATED, new org.apache.thrift.meta_data.FieldMetaData("dateCreated", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATE_MODIFIED, new org.apache.thrift.meta_data.FieldMetaData("dateModified", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TPage.class, metaDataMap);
  }

  public TPage() {
  }

  public TPage(
    int id,
    int parentId,
    String name,
    String link,
    String linkAlias,
    int ordering,
    String dateCreated,
    String dateModified)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.parentId = parentId;
    setParentIdIsSet(true);
    this.name = name;
    this.link = link;
    this.linkAlias = linkAlias;
    this.ordering = ordering;
    setOrderingIsSet(true);
    this.dateCreated = dateCreated;
    this.dateModified = dateModified;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPage(TPage other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.parentId = other.parentId;
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetLink()) {
      this.link = other.link;
    }
    if (other.isSetLinkAlias()) {
      this.linkAlias = other.linkAlias;
    }
    this.ordering = other.ordering;
    if (other.isSetDateCreated()) {
      this.dateCreated = other.dateCreated;
    }
    if (other.isSetDateModified()) {
      this.dateModified = other.dateModified;
    }
  }

  public TPage deepCopy() {
    return new TPage(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setParentIdIsSet(false);
    this.parentId = 0;
    this.name = null;
    this.link = null;
    this.linkAlias = null;
    setOrderingIsSet(false);
    this.ordering = 0;
    this.dateCreated = null;
    this.dateModified = null;
  }

  public int getId() {
    return this.id;
  }

  public TPage setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getParentId() {
    return this.parentId;
  }

  public TPage setParentId(int parentId) {
    this.parentId = parentId;
    setParentIdIsSet(true);
    return this;
  }

  public void unsetParentId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PARENTID_ISSET_ID);
  }

  /** Returns true if field parentId is set (has been assigned a value) and false otherwise */
  public boolean isSetParentId() {
    return EncodingUtils.testBit(__isset_bitfield, __PARENTID_ISSET_ID);
  }

  public void setParentIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PARENTID_ISSET_ID, value);
  }

  public String getName() {
    return this.name;
  }

  public TPage setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getLink() {
    return this.link;
  }

  public TPage setLink(String link) {
    this.link = link;
    return this;
  }

  public void unsetLink() {
    this.link = null;
  }

  /** Returns true if field link is set (has been assigned a value) and false otherwise */
  public boolean isSetLink() {
    return this.link != null;
  }

  public void setLinkIsSet(boolean value) {
    if (!value) {
      this.link = null;
    }
  }

  public String getLinkAlias() {
    return this.linkAlias;
  }

  public TPage setLinkAlias(String linkAlias) {
    this.linkAlias = linkAlias;
    return this;
  }

  public void unsetLinkAlias() {
    this.linkAlias = null;
  }

  /** Returns true if field linkAlias is set (has been assigned a value) and false otherwise */
  public boolean isSetLinkAlias() {
    return this.linkAlias != null;
  }

  public void setLinkAliasIsSet(boolean value) {
    if (!value) {
      this.linkAlias = null;
    }
  }

  public int getOrdering() {
    return this.ordering;
  }

  public TPage setOrdering(int ordering) {
    this.ordering = ordering;
    setOrderingIsSet(true);
    return this;
  }

  public void unsetOrdering() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ORDERING_ISSET_ID);
  }

  /** Returns true if field ordering is set (has been assigned a value) and false otherwise */
  public boolean isSetOrdering() {
    return EncodingUtils.testBit(__isset_bitfield, __ORDERING_ISSET_ID);
  }

  public void setOrderingIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ORDERING_ISSET_ID, value);
  }

  public String getDateCreated() {
    return this.dateCreated;
  }

  public TPage setDateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
    return this;
  }

  public void unsetDateCreated() {
    this.dateCreated = null;
  }

  /** Returns true if field dateCreated is set (has been assigned a value) and false otherwise */
  public boolean isSetDateCreated() {
    return this.dateCreated != null;
  }

  public void setDateCreatedIsSet(boolean value) {
    if (!value) {
      this.dateCreated = null;
    }
  }

  public String getDateModified() {
    return this.dateModified;
  }

  public TPage setDateModified(String dateModified) {
    this.dateModified = dateModified;
    return this;
  }

  public void unsetDateModified() {
    this.dateModified = null;
  }

  /** Returns true if field dateModified is set (has been assigned a value) and false otherwise */
  public boolean isSetDateModified() {
    return this.dateModified != null;
  }

  public void setDateModifiedIsSet(boolean value) {
    if (!value) {
      this.dateModified = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case PARENT_ID:
      if (value == null) {
        unsetParentId();
      } else {
        setParentId((Integer)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case LINK:
      if (value == null) {
        unsetLink();
      } else {
        setLink((String)value);
      }
      break;

    case LINK_ALIAS:
      if (value == null) {
        unsetLinkAlias();
      } else {
        setLinkAlias((String)value);
      }
      break;

    case ORDERING:
      if (value == null) {
        unsetOrdering();
      } else {
        setOrdering((Integer)value);
      }
      break;

    case DATE_CREATED:
      if (value == null) {
        unsetDateCreated();
      } else {
        setDateCreated((String)value);
      }
      break;

    case DATE_MODIFIED:
      if (value == null) {
        unsetDateModified();
      } else {
        setDateModified((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Integer.valueOf(getId());

    case PARENT_ID:
      return Integer.valueOf(getParentId());

    case NAME:
      return getName();

    case LINK:
      return getLink();

    case LINK_ALIAS:
      return getLinkAlias();

    case ORDERING:
      return Integer.valueOf(getOrdering());

    case DATE_CREATED:
      return getDateCreated();

    case DATE_MODIFIED:
      return getDateModified();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case PARENT_ID:
      return isSetParentId();
    case NAME:
      return isSetName();
    case LINK:
      return isSetLink();
    case LINK_ALIAS:
      return isSetLinkAlias();
    case ORDERING:
      return isSetOrdering();
    case DATE_CREATED:
      return isSetDateCreated();
    case DATE_MODIFIED:
      return isSetDateModified();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TPage)
      return this.equals((TPage)that);
    return false;
  }

  public boolean equals(TPage that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_parentId = true;
    boolean that_present_parentId = true;
    if (this_present_parentId || that_present_parentId) {
      if (!(this_present_parentId && that_present_parentId))
        return false;
      if (this.parentId != that.parentId)
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_link = true && this.isSetLink();
    boolean that_present_link = true && that.isSetLink();
    if (this_present_link || that_present_link) {
      if (!(this_present_link && that_present_link))
        return false;
      if (!this.link.equals(that.link))
        return false;
    }

    boolean this_present_linkAlias = true && this.isSetLinkAlias();
    boolean that_present_linkAlias = true && that.isSetLinkAlias();
    if (this_present_linkAlias || that_present_linkAlias) {
      if (!(this_present_linkAlias && that_present_linkAlias))
        return false;
      if (!this.linkAlias.equals(that.linkAlias))
        return false;
    }

    boolean this_present_ordering = true;
    boolean that_present_ordering = true;
    if (this_present_ordering || that_present_ordering) {
      if (!(this_present_ordering && that_present_ordering))
        return false;
      if (this.ordering != that.ordering)
        return false;
    }

    boolean this_present_dateCreated = true && this.isSetDateCreated();
    boolean that_present_dateCreated = true && that.isSetDateCreated();
    if (this_present_dateCreated || that_present_dateCreated) {
      if (!(this_present_dateCreated && that_present_dateCreated))
        return false;
      if (!this.dateCreated.equals(that.dateCreated))
        return false;
    }

    boolean this_present_dateModified = true && this.isSetDateModified();
    boolean that_present_dateModified = true && that.isSetDateModified();
    if (this_present_dateModified || that_present_dateModified) {
      if (!(this_present_dateModified && that_present_dateModified))
        return false;
      if (!this.dateModified.equals(that.dateModified))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TPage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParentId()).compareTo(other.isSetParentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parentId, other.parentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLink()).compareTo(other.isSetLink());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLink()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.link, other.link);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLinkAlias()).compareTo(other.isSetLinkAlias());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLinkAlias()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.linkAlias, other.linkAlias);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOrdering()).compareTo(other.isSetOrdering());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrdering()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ordering, other.ordering);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDateCreated()).compareTo(other.isSetDateCreated());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDateCreated()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dateCreated, other.dateCreated);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDateModified()).compareTo(other.isSetDateModified());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDateModified()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dateModified, other.dateModified);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TPage(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("parentId:");
    sb.append(this.parentId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("link:");
    if (this.link == null) {
      sb.append("null");
    } else {
      sb.append(this.link);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("linkAlias:");
    if (this.linkAlias == null) {
      sb.append("null");
    } else {
      sb.append(this.linkAlias);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ordering:");
    sb.append(this.ordering);
    first = false;
    if (!first) sb.append(", ");
    sb.append("dateCreated:");
    if (this.dateCreated == null) {
      sb.append("null");
    } else {
      sb.append(this.dateCreated);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("dateModified:");
    if (this.dateModified == null) {
      sb.append("null");
    } else {
      sb.append(this.dateModified);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TPageStandardSchemeFactory implements SchemeFactory {
    public TPageStandardScheme getScheme() {
      return new TPageStandardScheme();
    }
  }

  private static class TPageStandardScheme extends StandardScheme<TPage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TPage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PARENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.parentId = iprot.readI32();
              struct.setParentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // LINK
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.link = iprot.readString();
              struct.setLinkIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LINK_ALIAS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.linkAlias = iprot.readString();
              struct.setLinkAliasIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ORDERING
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.ordering = iprot.readI32();
              struct.setOrderingIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // DATE_CREATED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dateCreated = iprot.readString();
              struct.setDateCreatedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // DATE_MODIFIED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dateModified = iprot.readString();
              struct.setDateModifiedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TPage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PARENT_ID_FIELD_DESC);
      oprot.writeI32(struct.parentId);
      oprot.writeFieldEnd();
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.link != null) {
        oprot.writeFieldBegin(LINK_FIELD_DESC);
        oprot.writeString(struct.link);
        oprot.writeFieldEnd();
      }
      if (struct.linkAlias != null) {
        oprot.writeFieldBegin(LINK_ALIAS_FIELD_DESC);
        oprot.writeString(struct.linkAlias);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ORDERING_FIELD_DESC);
      oprot.writeI32(struct.ordering);
      oprot.writeFieldEnd();
      if (struct.dateCreated != null) {
        oprot.writeFieldBegin(DATE_CREATED_FIELD_DESC);
        oprot.writeString(struct.dateCreated);
        oprot.writeFieldEnd();
      }
      if (struct.dateModified != null) {
        oprot.writeFieldBegin(DATE_MODIFIED_FIELD_DESC);
        oprot.writeString(struct.dateModified);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TPageTupleSchemeFactory implements SchemeFactory {
    public TPageTupleScheme getScheme() {
      return new TPageTupleScheme();
    }
  }

  private static class TPageTupleScheme extends TupleScheme<TPage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TPage struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetParentId()) {
        optionals.set(1);
      }
      if (struct.isSetName()) {
        optionals.set(2);
      }
      if (struct.isSetLink()) {
        optionals.set(3);
      }
      if (struct.isSetLinkAlias()) {
        optionals.set(4);
      }
      if (struct.isSetOrdering()) {
        optionals.set(5);
      }
      if (struct.isSetDateCreated()) {
        optionals.set(6);
      }
      if (struct.isSetDateModified()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetParentId()) {
        oprot.writeI32(struct.parentId);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetLink()) {
        oprot.writeString(struct.link);
      }
      if (struct.isSetLinkAlias()) {
        oprot.writeString(struct.linkAlias);
      }
      if (struct.isSetOrdering()) {
        oprot.writeI32(struct.ordering);
      }
      if (struct.isSetDateCreated()) {
        oprot.writeString(struct.dateCreated);
      }
      if (struct.isSetDateModified()) {
        oprot.writeString(struct.dateModified);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TPage struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.parentId = iprot.readI32();
        struct.setParentIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.link = iprot.readString();
        struct.setLinkIsSet(true);
      }
      if (incoming.get(4)) {
        struct.linkAlias = iprot.readString();
        struct.setLinkAliasIsSet(true);
      }
      if (incoming.get(5)) {
        struct.ordering = iprot.readI32();
        struct.setOrderingIsSet(true);
      }
      if (incoming.get(6)) {
        struct.dateCreated = iprot.readString();
        struct.setDateCreatedIsSet(true);
      }
      if (incoming.get(7)) {
        struct.dateModified = iprot.readString();
        struct.setDateModifiedIsSet(true);
      }
    }
  }

}

