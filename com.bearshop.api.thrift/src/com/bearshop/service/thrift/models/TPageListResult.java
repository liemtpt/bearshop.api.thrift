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

public class TPageListResult implements org.apache.thrift.TBase<TPageListResult, TPageListResult._Fields>, java.io.Serializable, Cloneable, Comparable<TPageListResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TPageListResult");

  private static final org.apache.thrift.protocol.TField ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("error", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField LIST_DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("listData", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_RECORD_FIELD_DESC = new org.apache.thrift.protocol.TField("totalRecord", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TPageListResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TPageListResultTupleSchemeFactory());
  }

  public short error; // required
  public List<TPage> listData; // required
  public long totalRecord; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR((short)1, "error"),
    LIST_DATA((short)2, "listData"),
    TOTAL_RECORD((short)3, "totalRecord");

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
        case 1: // ERROR
          return ERROR;
        case 2: // LIST_DATA
          return LIST_DATA;
        case 3: // TOTAL_RECORD
          return TOTAL_RECORD;
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
  private static final int __ERROR_ISSET_ID = 0;
  private static final int __TOTALRECORD_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR, new org.apache.thrift.meta_data.FieldMetaData("error", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.LIST_DATA, new org.apache.thrift.meta_data.FieldMetaData("listData", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPage.class))));
    tmpMap.put(_Fields.TOTAL_RECORD, new org.apache.thrift.meta_data.FieldMetaData("totalRecord", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TPageListResult.class, metaDataMap);
  }

  public TPageListResult() {
  }

  public TPageListResult(
    short error,
    List<TPage> listData,
    long totalRecord)
  {
    this();
    this.error = error;
    setErrorIsSet(true);
    this.listData = listData;
    this.totalRecord = totalRecord;
    setTotalRecordIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPageListResult(TPageListResult other) {
    __isset_bitfield = other.__isset_bitfield;
    this.error = other.error;
    if (other.isSetListData()) {
      List<TPage> __this__listData = new ArrayList<TPage>(other.listData.size());
      for (TPage other_element : other.listData) {
        __this__listData.add(new TPage(other_element));
      }
      this.listData = __this__listData;
    }
    this.totalRecord = other.totalRecord;
  }

  public TPageListResult deepCopy() {
    return new TPageListResult(this);
  }

  @Override
  public void clear() {
    setErrorIsSet(false);
    this.error = 0;
    this.listData = null;
    setTotalRecordIsSet(false);
    this.totalRecord = 0;
  }

  public short getError() {
    return this.error;
  }

  public TPageListResult setError(short error) {
    this.error = error;
    setErrorIsSet(true);
    return this;
  }

  public void unsetError() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ERROR_ISSET_ID);
  }

  /** Returns true if field error is set (has been assigned a value) and false otherwise */
  public boolean isSetError() {
    return EncodingUtils.testBit(__isset_bitfield, __ERROR_ISSET_ID);
  }

  public void setErrorIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ERROR_ISSET_ID, value);
  }

  public int getListDataSize() {
    return (this.listData == null) ? 0 : this.listData.size();
  }

  public java.util.Iterator<TPage> getListDataIterator() {
    return (this.listData == null) ? null : this.listData.iterator();
  }

  public void addToListData(TPage elem) {
    if (this.listData == null) {
      this.listData = new ArrayList<TPage>();
    }
    this.listData.add(elem);
  }

  public List<TPage> getListData() {
    return this.listData;
  }

  public TPageListResult setListData(List<TPage> listData) {
    this.listData = listData;
    return this;
  }

  public void unsetListData() {
    this.listData = null;
  }

  /** Returns true if field listData is set (has been assigned a value) and false otherwise */
  public boolean isSetListData() {
    return this.listData != null;
  }

  public void setListDataIsSet(boolean value) {
    if (!value) {
      this.listData = null;
    }
  }

  public long getTotalRecord() {
    return this.totalRecord;
  }

  public TPageListResult setTotalRecord(long totalRecord) {
    this.totalRecord = totalRecord;
    setTotalRecordIsSet(true);
    return this;
  }

  public void unsetTotalRecord() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTALRECORD_ISSET_ID);
  }

  /** Returns true if field totalRecord is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalRecord() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTALRECORD_ISSET_ID);
  }

  public void setTotalRecordIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTALRECORD_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ERROR:
      if (value == null) {
        unsetError();
      } else {
        setError((Short)value);
      }
      break;

    case LIST_DATA:
      if (value == null) {
        unsetListData();
      } else {
        setListData((List<TPage>)value);
      }
      break;

    case TOTAL_RECORD:
      if (value == null) {
        unsetTotalRecord();
      } else {
        setTotalRecord((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR:
      return Short.valueOf(getError());

    case LIST_DATA:
      return getListData();

    case TOTAL_RECORD:
      return Long.valueOf(getTotalRecord());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ERROR:
      return isSetError();
    case LIST_DATA:
      return isSetListData();
    case TOTAL_RECORD:
      return isSetTotalRecord();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TPageListResult)
      return this.equals((TPageListResult)that);
    return false;
  }

  public boolean equals(TPageListResult that) {
    if (that == null)
      return false;

    boolean this_present_error = true;
    boolean that_present_error = true;
    if (this_present_error || that_present_error) {
      if (!(this_present_error && that_present_error))
        return false;
      if (this.error != that.error)
        return false;
    }

    boolean this_present_listData = true && this.isSetListData();
    boolean that_present_listData = true && that.isSetListData();
    if (this_present_listData || that_present_listData) {
      if (!(this_present_listData && that_present_listData))
        return false;
      if (!this.listData.equals(that.listData))
        return false;
    }

    boolean this_present_totalRecord = true;
    boolean that_present_totalRecord = true;
    if (this_present_totalRecord || that_present_totalRecord) {
      if (!(this_present_totalRecord && that_present_totalRecord))
        return false;
      if (this.totalRecord != that.totalRecord)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TPageListResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetError()).compareTo(other.isSetError());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetError()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.error, other.error);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetListData()).compareTo(other.isSetListData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetListData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.listData, other.listData);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTotalRecord()).compareTo(other.isSetTotalRecord());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalRecord()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalRecord, other.totalRecord);
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
    StringBuilder sb = new StringBuilder("TPageListResult(");
    boolean first = true;

    sb.append("error:");
    sb.append(this.error);
    first = false;
    if (!first) sb.append(", ");
    sb.append("listData:");
    if (this.listData == null) {
      sb.append("null");
    } else {
      sb.append(this.listData);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalRecord:");
    sb.append(this.totalRecord);
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

  private static class TPageListResultStandardSchemeFactory implements SchemeFactory {
    public TPageListResultStandardScheme getScheme() {
      return new TPageListResultStandardScheme();
    }
  }

  private static class TPageListResultStandardScheme extends StandardScheme<TPageListResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TPageListResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERROR
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.error = iprot.readI16();
              struct.setErrorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LIST_DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list24 = iprot.readListBegin();
                struct.listData = new ArrayList<TPage>(_list24.size);
                for (int _i25 = 0; _i25 < _list24.size; ++_i25)
                {
                  TPage _elem26;
                  _elem26 = new TPage();
                  _elem26.read(iprot);
                  struct.listData.add(_elem26);
                }
                iprot.readListEnd();
              }
              struct.setListDataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_RECORD
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.totalRecord = iprot.readI64();
              struct.setTotalRecordIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TPageListResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ERROR_FIELD_DESC);
      oprot.writeI16(struct.error);
      oprot.writeFieldEnd();
      if (struct.listData != null) {
        oprot.writeFieldBegin(LIST_DATA_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.listData.size()));
          for (TPage _iter27 : struct.listData)
          {
            _iter27.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TOTAL_RECORD_FIELD_DESC);
      oprot.writeI64(struct.totalRecord);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TPageListResultTupleSchemeFactory implements SchemeFactory {
    public TPageListResultTupleScheme getScheme() {
      return new TPageListResultTupleScheme();
    }
  }

  private static class TPageListResultTupleScheme extends TupleScheme<TPageListResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TPageListResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetError()) {
        optionals.set(0);
      }
      if (struct.isSetListData()) {
        optionals.set(1);
      }
      if (struct.isSetTotalRecord()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetError()) {
        oprot.writeI16(struct.error);
      }
      if (struct.isSetListData()) {
        {
          oprot.writeI32(struct.listData.size());
          for (TPage _iter28 : struct.listData)
          {
            _iter28.write(oprot);
          }
        }
      }
      if (struct.isSetTotalRecord()) {
        oprot.writeI64(struct.totalRecord);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TPageListResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.error = iprot.readI16();
        struct.setErrorIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list29 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.listData = new ArrayList<TPage>(_list29.size);
          for (int _i30 = 0; _i30 < _list29.size; ++_i30)
          {
            TPage _elem31;
            _elem31 = new TPage();
            _elem31.read(iprot);
            struct.listData.add(_elem31);
          }
        }
        struct.setListDataIsSet(true);
      }
      if (incoming.get(2)) {
        struct.totalRecord = iprot.readI64();
        struct.setTotalRecordIsSet(true);
      }
    }
  }

}
